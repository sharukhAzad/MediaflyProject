#Run Tests

    To test the application provided for this hiring project, I created a test script to test the entire end-to-end process to make sure all
    functionalities are working as intended.

    To begin, please run the following commands in a seperate terminals in the specified order:
        python application.py
        python queue.py
        python worker.py

    Once the application server is up, run the test script named "HiringProject_TestScript".

    **The test script was written using the TestNG framework to make reading the code much easier.
    
    **The "Abstract_Class" located in the ReusableLibrary folder stores the Before/After Suites and Methods,
    that begins the WebDriver and Loggers, and quits the WebDriver when the test is complete.

    **The "Reusable_Actions" located in the ReusableLibrary folder contains a method that defines the WebDriver,
    later in which the "Abstract_Class" calls upon.

    The test script contains 6 different test cases, with every action logged into an HTML Report. 
    
    Test Case 1 "getStatusCodeForApplicationServer" simply navigates to the application server and checks to see if
    it can load correctly. The action is logged whether it passes or fails. This test case also receives the status code
    via the use of Rest Assured and if the status code matches "200", the test is successful.

    Test Case 2 "chooseAndUploadImageUI" lets the user select an image via the UI and then click on the "Upload Image"  
    button to successfully upload the image. Actions are held in a "Try/Catch" so that we can see error codes if it
    were to fail.

    Test Case 3 "uploadNonImageFile" was done for negative testing purposes. The user is allowed to upload a
    non-image file to the application. This will be discussed down below.

    Test Case 4 "postImageDirectlyAPI" allows user to post an image via API using Rest Assured. Test also
    looks for status code "302", which deems is successful. Image is then sent to the Queue, Processed,
    and then sent back out to the UI. 

    Test Case 5 "viewImages" automates the process of viewing both original images uploaded via UI and API, and 
    also views both processed images. This is done to show that the application runs as intended.

    Test Case 6 "getImageResponse" retrieves the body of the "images.json" file and prints it to the HTML Report.
    This body shows the status of the photos posted. Also shows any failed instances.

    **The HTML report correctly shows all different test case results and whether they pass or failed.

==========

#Bugs Identified 

    Test Case 3 "uploadNonImageFile" shows a bug in the system. User is allowed to upload any file, and is not just
    limited to image file. File is uploaded but (not surprisingly), the file cannot be processed as intended
    since it is not an image file. File however can be viewed or downloaded. This is a high level bug.

    To recreate bug:
    1. Navigate to application server page.
    2. Click on "Choose File".
    3. Select any non-image file. (this example used a .txt file)
    4. Click on "Upload Image".
    5. File will appear in the chart labeled as "Failed".
    6. Click on "Original" to view .txt file or download it.
