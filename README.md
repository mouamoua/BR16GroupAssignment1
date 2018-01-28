# Generic Setup Instructions
1. Clone the repository
2. Import the project into Eclipse. I went to **File** > **Import**, and then chose **General** > **Existing Project into Workspace**. Typically, the default options here do not copy the project files into the workspace. Instead, it leaves the project files in the directory in which the repository was cloned. For GitHub, the default path is typically **Documents/GitHub**.

## Tracking external dependencies pre-build
For the UI and for any other libraries used, the Jars must be added to your project build path. When you begin using a new Jar, the following process should set us up all to be able to pull the code down and have a working project.
1. Copy the Jar into the src folder (following the relative path laid out originally by Robert's swt Jar usage).
2. Right-click the top-level project folder, and choose **Build Path** > **Configure Build Path**
3. Click the **Libraries** tab, click **Add Jars**, and choose the Jar you copied to the src folder.
4. Optionally, check that the .classpath file was updated with a path to the Jar that is relative to the project, and not somewhere else on your machine.
