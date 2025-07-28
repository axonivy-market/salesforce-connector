package com.axonivy.connector.salesforce.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Lead implements Serializable {

	private static final long serialVersionUID = 1L;

	public class Address{
	    public String city;
	    public String country;
	    public String geocodeAccuracy;
	    public String latitude;
	    public String longitude;
	    public String postalCode;
	    public String state;
	    public String street;
	}

	    @JsonProperty("Id") 
	    private String id;
	    @JsonProperty("IsDeleted") 
	    private Boolean isDeleted;
	    @JsonProperty("MasterRecordId") 
	    private String masterRecordId;
	    @JsonProperty("LastName") 
	    private String lastName;
	    @JsonProperty("FirstName") 
	    private String firstName;
	    @JsonProperty("Salutation") 
	    private String salutation;
	    @JsonProperty("Name") 
	    private String name;
	    @JsonProperty("Title") 
	    private String title;
	    @JsonProperty("Company") 
	    private String company;
	    @JsonProperty("Street") 
	    private String street;
	    @JsonProperty("City") 
	    private String city;
	    @JsonProperty("State") 
	    private String state;
	    @JsonProperty("PostalCode") 
	    private String postalCode;
	    @JsonProperty("Country") 
	    private String country;
	    @JsonProperty("Latitude") 
	    private String latitude;
	    @JsonProperty("Longitude") 
	    private String longitude;
	    @JsonProperty("GeocodeAccuracy") 
	    private String geocodeAccuracy;
	    @JsonProperty("Address") 
	    private Address address;
	    @JsonProperty("Phone") 
	    private String phone;
	    @JsonProperty("MobilePhone") 
	    private String mobilePhone;
	    @JsonProperty("Fax") 
	    private String fax;
	    @JsonProperty("Email") 
	    private String email;
	    @JsonProperty("Website") 
	    private String website;
	    @JsonProperty("PhotoUrl") 
	    private String photoUrl;
	    @JsonProperty("Description") 
	    private String description;
	    @JsonProperty("LeadSource") 
	    private String leadSource;
	    @JsonProperty("Status") 
	    private String status;
	    @JsonProperty("Industry") 
	    private String industry;
	    @JsonProperty("Rating") 
	    private String rating;
	    @JsonProperty("AnnualRevenue") 
	    private double annualRevenue;
	    @JsonProperty("NumberOfEmployees") 
	    private String numberOfEmployees;
	    @JsonProperty("OwnerId") 
	    private String ownerId;
	    @JsonProperty("IsConverted") 
	    private Boolean isConverted;
	    @JsonProperty("ConvertedDate") 
	    private Date convertedDate;
	    @JsonProperty("ConvertedAccountId") 
	    private String convertedAccountId;
	    @JsonProperty("ConvertedContactId") 
	    private String convertedContactId;
	    @JsonProperty("ConvertedOpportunityId") 
	    private String convertedOpportunityId;
	    @JsonProperty("IsUnreadByOwner") 
	    private Boolean isUnreadByOwner;
	    @JsonProperty("CreatedDate") 
	    private Date createdDate;
	    @JsonProperty("CreatedById") 
	    private String createdById;
	    @JsonProperty("LastModifiedDate") 
	    private Date lastModifiedDate;
	    @JsonProperty("LastModifiedById") 
	    private String lastModifiedById;
	    @JsonProperty("SystemModstamp") 
	    private Date systemModstamp;
	    @JsonProperty("LastActivityDate") 
	    private Date lastActivityDate;
	    @JsonProperty("LastViewedDate") 
	    private Date lastViewedDate;
	    @JsonProperty("LastReferencedDate") 
	    private Date lastReferencedDate;
	    @JsonProperty("Jigsaw") 
	    private String jigsaw;
	    @JsonProperty("JigsawContactId") 
	    private String jigsawContactId;
	    @JsonProperty("CleanStatus") 
	    private String cleanStatus;
	    @JsonProperty("CompanyDunsNumber") 
	    private String companyDunsNumber;
	    @JsonProperty("DandbCompanyId") 
	    private String dandbCompanyId;
	    @JsonProperty("EmailBouncedReason") 
	    private String emailBouncedReason;
	    @JsonProperty("EmailBouncedDate") 
	    private Date emailBouncedDate;
	    @JsonProperty("IndividualId") 
	    private String individualId;
	    @JsonProperty("SICCode__c") 
	    private String sICCode__c;
	    @JsonProperty("ProductInterest__c") 
	    private String productInterest__c;
	    @JsonProperty("Primary__c") 
	    private String primary__c;
	    @JsonProperty("CurrentGenerators__c") 
	    private String currentGenerators__c;
	    @JsonProperty("NumberofLocations__c") 
	    private double numberofLocations__c;
	    @JsonIgnore
	    private String accName;

	    
	    
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public Boolean getIsDeleted() {
			return isDeleted;
		}
		public void setIsDeleted(Boolean isDeleted) {
			this.isDeleted = isDeleted;
		}
		public String getMasterRecordId() {
			return masterRecordId;
		}
		public void setMasterRecordId(String masterRecordId) {
			this.masterRecordId = masterRecordId;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getSalutation() {
			return salutation;
		}
		public void setSalutation(String salutation) {
			this.salutation = salutation;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getCompany() {
			return company;
		}
		public void setCompany(String company) {
			this.company = company;
		}
		public String getStreet() {
			return street;
		}
		public void setStreet(String street) {
			this.street = street;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getPostalCode() {
			return postalCode;
		}
		public void setPostalCode(String postalCode) {
			this.postalCode = postalCode;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getLatitude() {
			return latitude;
		}
		public void setLatitude(String latitude) {
			this.latitude = latitude;
		}
		public String getLongitude() {
			return longitude;
		}
		public void setLongitude(String longitude) {
			this.longitude = longitude;
		}
		public String getGeocodeAccuracy() {
			return geocodeAccuracy;
		}
		public void setGeocodeAccuracy(String geocodeAccuracy) {
			this.geocodeAccuracy = geocodeAccuracy;
		}
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		public String getMobilePhone() {
			return mobilePhone;
		}
		public void setMobilePhone(String mobilePhone) {
			this.mobilePhone = mobilePhone;
		}
		public String getFax() {
			return fax;
		}
		public void setFax(String fax) {
			this.fax = fax;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getWebsite() {
			return website;
		}
		public void setWebsite(String website) {
			this.website = website;
		}
		public String getPhotoUrl() {
			return photoUrl;
		}
		public void setPhotoUrl(String photoUrl) {
			this.photoUrl = photoUrl;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getLeadSource() {
			return leadSource;
		}
		public void setLeadSource(String leadSource) {
			this.leadSource = leadSource;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getIndustry() {
			return industry;
		}
		public void setIndustry(String industry) {
			this.industry = industry;
		}
		public String getRating() {
			return rating;
		}
		public void setRating(String rating) {
			this.rating = rating;
		}
		public double getAnnualRevenue() {
			return annualRevenue;
		}
		public void setAnnualRevenue(double annualRevenue) {
			this.annualRevenue = annualRevenue;
		}
		public String getNumberOfEmployees() {
			return numberOfEmployees;
		}
		public void setNumberOfEmployees(String numberOfEmployees) {
			this.numberOfEmployees = numberOfEmployees;
		}
		public String getOwnerId() {
			return ownerId;
		}
		public void setOwnerId(String ownerId) {
			this.ownerId = ownerId;
		}
		public Boolean getIsConverted() {
			return isConverted;
		}
		public void setIsConverted(Boolean isConverted) {
			this.isConverted = isConverted;
		}
		public Date getConvertedDate() {
			return convertedDate;
		}
		public void setConvertedDate(Date convertedDate) {
			this.convertedDate = convertedDate;
		}
		public String getConvertedAccountId() {
			return convertedAccountId;
		}
		public void setConvertedAccountId(String convertedAccountId) {
			this.convertedAccountId = convertedAccountId;
		}
		public String getConvertedContactId() {
			return convertedContactId;
		}
		public void setConvertedContactId(String convertedContactId) {
			this.convertedContactId = convertedContactId;
		}
		public String getConvertedOpportunityId() {
			return convertedOpportunityId;
		}
		public void setConvertedOpportunityId(String convertedOpportunityId) {
			this.convertedOpportunityId = convertedOpportunityId;
		}
		public Boolean getIsUnreadByOwner() {
			return isUnreadByOwner;
		}
		public void setIsUnreadByOwner(Boolean isUnreadByOwner) {
			this.isUnreadByOwner = isUnreadByOwner;
		}
		public Date getCreatedDate() {
			return createdDate;
		}
		public void setCreatedDate(Date createdDate) {
			this.createdDate = createdDate;
		}
		public String getCreatedById() {
			return createdById;
		}
		public void setCreatedById(String createdById) {
			this.createdById = createdById;
		}
		public Date getLastModifiedDate() {
			return lastModifiedDate;
		}
		public void setLastModifiedDate(Date lastModifiedDate) {
			this.lastModifiedDate = lastModifiedDate;
		}
		public String getLastModifiedById() {
			return lastModifiedById;
		}
		public void setLastModifiedById(String lastModifiedById) {
			this.lastModifiedById = lastModifiedById;
		}
		public Date getSystemModstamp() {
			return systemModstamp;
		}
		public void setSystemModstamp(Date systemModstamp) {
			this.systemModstamp = systemModstamp;
		}
		public Date  getLastActivityDate() {
			return lastActivityDate;
		}
		public void setLastActivityDate(Date lastActivityDate) {
			this.lastActivityDate = lastActivityDate;
		}
		public Date getLastViewedDate() {
			return lastViewedDate;
		}
		public void setLastViewedDate(Date lastViewedDate) {
			this.lastViewedDate = lastViewedDate;
		}
		public Date getLastReferencedDate() {
			return lastReferencedDate;
		}
		public void setLastReferencedDate(Date lastReferencedDate) {
			this.lastReferencedDate = lastReferencedDate;
		}
		public String getJigsaw() {
			return jigsaw;
		}
		public void setJigsaw(String jigsaw) {
			this.jigsaw = jigsaw;
		}
		public String getJigsawContactId() {
			return jigsawContactId;
		}
		public void setJigsawContactId(String jigsawContactId) {
			this.jigsawContactId = jigsawContactId;
		}
		public String getCleanStatus() {
			return cleanStatus;
		}
		public void setCleanStatus(String cleanStatus) {
			this.cleanStatus = cleanStatus;
		}
		public String getCompanyDunsNumber() {
			return companyDunsNumber;
		}
		public void setCompanyDunsNumber(String companyDunsNumber) {
			this.companyDunsNumber = companyDunsNumber;
		}
		public String getDandbCompanyId() {
			return dandbCompanyId;
		}
		public void setDandbCompanyId(String dandbCompanyId) {
			this.dandbCompanyId = dandbCompanyId;
		}
		public String getEmailBouncedReason() {
			return emailBouncedReason;
		}
		public void setEmailBouncedReason(String emailBouncedReason) {
			this.emailBouncedReason = emailBouncedReason;
		}
		public Date getEmailBouncedDate() {
			return emailBouncedDate;
		}
		public void setEmailBouncedDate(Date emailBouncedDate) {
			this.emailBouncedDate = emailBouncedDate;
		}
		public String getIndividualId() {
			return individualId;
		}
		public void setIndividualId(String individualId) {
			this.individualId = individualId;
		}
		public String getsICCode__c() {
			return sICCode__c;
		}
		public void setsICCode__c(String sICCode__c) {
			this.sICCode__c = sICCode__c;
		}
		public String getProductInterest__c() {
			return productInterest__c;
		}
		public void setProductInterest__c(String productInterest__c) {
			this.productInterest__c = productInterest__c;
		}
		public String getPrimary__c() {
			return primary__c;
		}
		public void setPrimary__c(String primary__c) {
			this.primary__c = primary__c;
		}
		public String getCurrentGenerators__c() {
			return currentGenerators__c;
		}
		public void setCurrentGenerators__c(String currentGenerators__c) {
			this.currentGenerators__c = currentGenerators__c;
		}
		public double getNumberofLocations__c() {
			return numberofLocations__c;
		}
		public void setNumberofLocations__c(double numberofLocations__c) {
			this.numberofLocations__c = numberofLocations__c;
		}
		public String getAccName() {
			return accName;
		}
		public void setAccName(String accName) {
			this.accName = accName;
		}
		
		public String date2Str(Date d) {
			return d==null?"":Opportunity.dateFormat.get().format(d);
		}
		@Override
		public String toString() {
			return "Lead [isDeleted=" + isDeleted + ", masterRecordId=" + masterRecordId + ", lastName=" + lastName
					+ ", firstName=" + firstName + ", salutation=" + salutation + ", name=" + name + ", title=" + title
					+ ", company=" + company + ", street=" + street + ", city=" + city + ", state=" + state
					+ ", postalCode=" + postalCode + ", country=" + country + ", latitude=" + latitude + ", longitude="
					+ longitude + ", geocodeAccuracy=" + geocodeAccuracy + ", address=" + address + ", phone=" + phone
					+ ", mobilePhone=" + mobilePhone + ", fax=" + fax + ", email=" + email + ", website=" + website
					+ ", photoUrl=" + photoUrl + ", description=" + description + ", leadSource=" + leadSource
					+ ", status=" + status + ", industry=" + industry + ", rating=" + rating + ", annualRevenue="
					+ annualRevenue + ", numberOfEmployees=" + numberOfEmployees + ", ownerId=" + ownerId
					+ ", isConverted=" + isConverted + ", convertedDate=" + date2Str(convertedDate) + ", convertedAccountId="
					+ convertedAccountId + ", convertedContactId=" + convertedContactId + ", convertedOpportunityId="
					+ convertedOpportunityId + ", isUnreadByOwner=" + isUnreadByOwner + ", createdDate=" + date2Str(createdDate)
					+ ", createdById=" + createdById + ", lastModifiedDate=" + date2Str(lastModifiedDate) + ", lastModifiedById="
					+ lastModifiedById + ", systemModstamp=" + date2Str(systemModstamp) + ", lastActivityDate=" + date2Str(lastActivityDate)
					+ ", lastViewedDate=" + date2Str(lastViewedDate) + ", lastReferencedDate=" + date2Str(lastReferencedDate) + ", jigsaw="
					+ jigsaw + ", jigsawContactId=" + jigsawContactId + ", cleanStatus=" + cleanStatus
					+ ", companyDunsNumber=" + companyDunsNumber + ", dandbCompanyId=" + dandbCompanyId
					+ ", emailBouncedReason=" + emailBouncedReason + ", emailBouncedDate=" + date2Str(emailBouncedDate)
					+ ", individualId=" + individualId + ", sICCode__c=" + sICCode__c + ", productInterest__c="
					+ productInterest__c + ", primary__c=" + primary__c + ", currentGenerators__c="
					+ currentGenerators__c + ", numberofLocations__c=" + numberofLocations__c + ", accName=" + accName
					+ "]";
		}

	    
	    
}
