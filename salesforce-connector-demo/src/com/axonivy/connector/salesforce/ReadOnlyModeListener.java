package com.axonivy.connector.salesforce;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlCommandLink;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutcomeTargetButton;
import javax.faces.component.html.HtmlOutcomeTargetLink;
import javax.faces.component.html.HtmlOutputLink;
import javax.faces.component.visit.VisitCallback;
import javax.faces.component.visit.VisitContext;
import javax.faces.component.visit.VisitResult;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.texteditor.TextEditor;

import ch.ivyteam.ivy.environment.Ivy;

/**
 * Global read-only controlling
 *
 * Use in XHTML for your desired component 
 * <f:phaseListener binding="#{data.readOnlyModeListener}" />
 * 
 *  or
 * 
 * initialize in ui logic, init method 
 * 
 * in.readOnlyModeListener = new ReadOnlyModeListener(); in.readOnlyModeListener.enabled = in.isViewMode;
 */
public class ReadOnlyModeListener implements PhaseListener {
	
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_CONTAINER_ID = "form";

	private String containerId = DEFAULT_CONTAINER_ID;
	private boolean isEnabled;

	
	public ReadOnlyModeListener() {
		super();
	}

	public ReadOnlyModeListener(String containerId, boolean isEnabled) {
		super();
		this.containerId = containerId;
		this.isEnabled = isEnabled;
	}

	
	@Override
	public void afterPhase(PhaseEvent arg0) {
		// no log needed
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		FacesContext ctx = event.getFacesContext();
		UIViewRoot uiViewRoot = ctx.getViewRoot();

		if (uiViewRoot != null && isEnabled) {
			UIComponent container = findComponent(uiViewRoot, containerId);
			if (container != null) {
				setReadonlyMode(container);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.faces.event.PhaseListener#getPhaseId()
	 */
	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RENDER_RESPONSE;
	}

	/**
	 * When in edit mode sets specified fields to read only.
	 *
	 * @param parent the new edits the mode
	 */
	public void setEditMode(UIComponent parent) {
		setModifiedVisibility(parent, true, false);
	}

	/**
	 * When in read only mode sets specified fields to read only.
	 *
	 * @param parent the new readonly mode
	 */
	public void setReadonlyMode(UIComponent parent) {
		setModifiedVisibility(parent, false, false);
	}

	/**
	 * When read only mode is enabled, the fields will be set as read only.
	 *
	 * @param parent the UI container to be set as read only
	 */
	protected void setModifiedVisibility(UIComponent parent, boolean editMode, boolean forceEnable) {
		if (parent == null) {
			return;
		}
		for (Iterator<UIComponent> childIter = parent.getFacetsAndChildren(); childIter.hasNext();) {
			// Apply Read-only Mode for UIInput components
			UIComponent target = childIter.next();
			if(forceEnable) {
				disableOrHideComponent(editMode, target, false, false);
			} else {
				String styleClass = String.valueOf(target.getAttributes().get("styleClass"));
				List<String> styleClasses = Arrays.asList(styleClass.split(" "));
				
				if (!editMode && styleClasses.contains("doNotRenderInReadOnlyMode")) {
					target.setRendered(false);// skip elements which contain doNotRenderInReadOnlyMode styleclass
				} // doNotDisable is not set and editMode is not true -> disable the field , or editMode is true and doAllowEdit is not set -> disable the Field
				else if (!editMode && !styleClasses.contains("doNotDisable") || editMode && !styleClasses.contains("doAllowEdit")) {
					boolean DISABLE = true;
					disableOrHideComponent(editMode, target, false, DISABLE);
				} else {
					Ivy.log().debug("component " + parent.getClientId() + 
							" won't be disabled ( !editMode {0} && !styleClasses.contains(\"doNotDisable\") {1} || editMode {2} && !styleClasses.contains(\"doAllowEdit\" {3}) ", 
							!editMode, !styleClasses.contains("doNotDisable"), editMode, !styleClasses.contains("doAllowEdit"));
				}
			}
		}
	}

	private void disableOrHideComponent(boolean editMode, UIComponent target, boolean onlyInputs, boolean disable) {
		if (disable && target instanceof OutputLabel) {
			OutputLabel label = (OutputLabel) target;//do not show required asterisk for labels of read-only fields
			if(Boolean.TRUE.toString().equals(label.getIndicateRequired())){
				label.setIndicateRequired("false");
			}
		}
		else if (target instanceof UIInput) {
			UIInput input = (UIInput) target;
			disableUIInput(input, disable);

		} else if (onlyInputs) {
			// do nothing for other types than inputs
		} else if (target instanceof TextEditor) {
			TextEditor input = (TextEditor) target;
			input.setReadonly(disable);

		} else if (target instanceof DataTable) {
			DataTable dataTable = (DataTable) target;
			dataTable.setEditable(!disable);
		} else if (target instanceof HtmlCommandButton) {
			HtmlCommandButton button = (HtmlCommandButton) target;
			button.setRendered(!disable);
		} else if (target instanceof HtmlCommandLink) {
			HtmlCommandLink link = (HtmlCommandLink) target;
			link.setDisabled(disable);
		} else if (target instanceof HtmlOutcomeTargetButton) {
			HtmlOutcomeTargetButton link = (HtmlOutcomeTargetButton) target;
			link.setRendered(!disable);
		} else if (target instanceof HtmlOutcomeTargetLink) {
			HtmlOutcomeTargetLink link = (HtmlOutcomeTargetLink) target;
			link.setDisabled(disable);
		} else if (target instanceof HtmlOutputLink) {
			HtmlOutputLink link = (HtmlOutputLink) target;
			link.setDisabled(disable);
		}
		setModifiedVisibility(target,editMode,!disable);
	}

	private void disableUIInput(UIInput input ,boolean disable) {
		Method setDisabledMethod;
		if (input.getClass() != HtmlInputHidden.class) {
			try {
				setDisabledMethod = input.getClass().getMethod("setDisabled", boolean.class);

				if (setDisabledMethod != null) {
					setDisabledMethod.invoke(input, disable);
				}
			} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | SecurityException e) {
				Ivy.log().error("setDisabled method not working, the input disable value {0} wont be changed to : " + 
						disable, e, input.getId());
			}
		}
	}

	private UIComponent findComponent(UIViewRoot root, String id) {
		UIComponent[] found = new UIComponent[1];
		root.visitTree(VisitContext.createVisitContext(FacesContext.getCurrentInstance()), new VisitCallback() {
			@Override
			public VisitResult visit(VisitContext context, UIComponent component) {
				if (component.getId().equals(id)) {
					found[0] = component;
					return VisitResult.COMPLETE;
				}
				return VisitResult.ACCEPT;
			}
		});
		return found[0];
	}

	public String getContainerId() {
		return containerId;
	}

	public void setContainerId(String containerId) {
		this.containerId = containerId;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}

}
