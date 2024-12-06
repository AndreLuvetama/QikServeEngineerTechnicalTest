**<h2>Follow up questions</h2>**

**1. How long did you spend on the test? What would you add if you had more time?**<br/>
I spent 5 days on the test. If I had more time I would like to add a repository and test with the database, save, delete and edit the data.

**2. What was the most useful feature that was added to the latest version of your chosen language? Please include a snippet of code that shows how you've used it.**
<br/> The most useful feature that was added to the latest version of Java is the getLast and getFirst because this feature made using ArrayDeque much easier.

Deque<String> deque = new ArrayDeque<>();       
deque.addLast("Element 1");
deque.addFirst("Element 2");
System.out.println("First Element: " + deque.getFirst());
System.out.println("Last Element: " + deque.getLast());

**3. What did you find most difficult?**<br/>
The hardest part was using the wiremock, I have normally used another type of resource to do the test.

4. What mechanism did you put in place to track down issues in production on this code? If you didn’t put anything, write down what you could do.
I didn't put any mechanism to track down issues, but I  would like to use the Log4j and i can configure the elasticsearch for the MIcroservices.

**5. The Wiremock represents one source of information. We should be prepared to integrate with more sources. List the steps that we would need to take to add more sources of items with different formats and promotions.**<br/>
Here is the step we can use to add more item sources with different formats and promotions:
5.1 Define the Data Model: Create a common data model that 
represents products, the enuns, and promotions from different sources. 
5.2 Develop adapters or connectors for each data source to retrieve 
product and promotion data in their respective formats.
5.3 Handle data transformation logic within the adapters to map the fields
and formats from each source to the corresponding fields in the common
data model. 
5.4 Implement robust error handling and logging mechanisms to capture 
and log errors encountered during data retrieval and processing.

Questions<br/>
**2 Describe the most innovative or inventive endQuestionseavor you've undertaken. This could be your idea for a process change, a new product concept, a unique metric, or a novel customer interface. Do not share confidentail information! Provide context to help us understand the
innovation. What problem were you addressing, and what were the outcomes? Why was solving this problem important, and what was the impact of the change?**<br/>


PROPERTY EXCHANGE ONLY System. This is the system I developed with Spring boot and react js.The system allows user registration and the inclusion they property. When including the property, the owner informs the items of interest, the system, through the algorithm, matches properties with similar items and generates the Match. Once Match is made, the user will be able to start a negotiation to exchange property. 
The aim of this system is to facilitate the exchange of properties between two owners who do not know each other and who do not need to live in the same location.
Building the system allowed me to have extensive knowledge about the business model and also get to know the development tools in depth, in this case I used SPring boot on the back and react js on the front.


