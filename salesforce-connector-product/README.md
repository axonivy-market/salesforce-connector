# Salesforce-connector

This connector builds the REST API and provides a means to access Salesforce's resources. A client can utilize the REST API to manipulate Salesforce (SF) objects. For instance, to perform **CRUD** operations on an Opportunity object or to query and retrieve data. Salesforce Connector:

- Provides the standard set of Rest APIs to manipulate one specific object of SalesForce Opportunity.
- Track the status and the number of each status Opportunity.
- Manipulate the object Opportunity.
- Build a standard Rest client to apply Salesforce Object Query Language (SOQL).


# Configuration

  ## Configure on the SF Application
  For a client application to access REST API resources, it must be authorized as a trusted visitor. To implement this authorization, utilize a **Connected App** and employ the **OAuth 2.0** authorization flow.

  **Important steps**
  
    - Create a Connected App to generate the necessary authentication information.
    - Configure authentication providers for your application.
    - Activate the required settings in OAuth and OpenID Connect settings to facilitate secure authentication.
    - Set up Named Credentials to manage and secure access to external services.


  ## Integrate Connector into the Project
  The **Consumer Key** and **Consumer Secret** are retrieved from the **Connected App** and placed in the `variables.yaml` file.

  Authentication information is set up as a global variable and can be updated anytime.

  
  | Variable name                              | Note                                          |Example                                                                               |
  |--------------------------------------------|-----------------------------------------------|--------------------------------------------------------------------------------------|
  |salesforce-connector.auth.subdomain         |The domain that Salesforce provides to consumers for authentication         |axonactive3-dev-ed.develop                                                            |
  |salesforce-connector.auth.clientId          |Consumer Id                                    |3MVG95mg0lk4bathQF4Z_F1GcZZPr8ztvo29c53HhwOXnCKBkP8LkxHnb5KlydXj3Oomw0VHsY |
  |salesforce-connector.auth.clientSecret      |Consumer Secret                                |56C6CA448B49032828FE4C4DF16D1AF4804B8CC734E066B255A5B31A                     |
  |salesforce-connector.auth.useAppPermissions |OAuth2 Grantype                                |true (client_credentials)                                                             |
  |salesforce-connector.auth.url               |Salesforce Rest URI                            |https://axonactive3-dev-ed.develop.my.salesforce.com/services/data/v58.0              |



# Demo
  1. Login to the Axonivy
  2. Click **Starts** in the Axon Ivy menu to open the Full Process List
  3. On the Full Process List, click on **ChartDemo**.
  
  ![plot](/salesforce-connector-product/doc/img/sf-start-chart.png)
  
  4. A simple chart for statistical analysis:
  
  ![plot](/salesforce-connector-product/doc/img/sf-chart.png)
  
  5. On the Full Process List, click on **ListDemo**.
  
  ![plot](/salesforce-connector-product/doc/img/sf-start-list.png)
  
  6. Opportunity Overview:
  
  ![plot](/salesforce-connector-product/doc/img/sf-list.png)
  
  7. Click on the Add New button:
  
  ![plot](/salesforce-connector-product/doc/img/sf-start-addNew.png)
  
  8. Add new Opportunity:
  
  ![plot](/salesforce-connector-product/doc/img/sf-addNew.png)
  
  9. Click on Opportunity Name to view details:
  
  ![plot](/salesforce-connector-product/doc/img/sf-start-detail.png)
  
  10. Opportunity details:
  
  ![plot](/salesforce-connector-product/doc/img/sf-detail.png)
  
  11. Opportunity details Activity:
  
  ![plot](/salesforce-connector-product/doc/img/sf-detail-activity.png)
  
  12. Click on the icon Pencil to Update an Opportunity:
  
  ![plot](/salesforce-connector-product/doc/img/sf-start-edit.png)
  
  13. Edit Opportunity:
  
  ![plot](/salesforce-connector-product/doc/img/sf-edit.png)
  
  14. Click on the icon Trash to remove an Opportunity:
  
  ![plot](/salesforce-connector-product/doc/img/sf-start-delete.png)
  
  15. Delete Opportunity:
  
  ![plot](/salesforce-connector-product/doc/img/sf-delete.png)


# Resources
[Developer](https://developer.salesforce.com/docs)

[Connected App](https://help.salesforce.com/s/articleView?id=sf.connected_app_client_credentials_setup.htm&type=5)

[Salesforce SOQL](https://developer.salesforce.com/docs/atlas.en-us.soql_sosl.meta/soql_sosl/sforce_api_calls_soql.htm)


