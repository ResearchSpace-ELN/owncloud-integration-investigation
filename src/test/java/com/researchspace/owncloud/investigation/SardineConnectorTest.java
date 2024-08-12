package com.researchspace.owncloud.investigation;

import java.io.IOException;
import org.junit.jupiter.api.Test;

public class SardineConnectorTest {

  private String owncloudHost = "https://owncloud-test.researchspace.com/remote.php/webdav";
  private String owncloudUsername = "<owncloud username>";
  private String owncloudPassword = "<owncloud password>";

  private String nextcloudHost = "https://nextcloud-testing.researchspace.com/remote.php/webdav";
  private String nextcloudUsername = "<nextcloud username>";
  private String nextcloudPassword = "<nextcloud password>";

  @Test
  public void owncloudTest() throws IOException {
    SardineConnector connector = new SardineConnector(
        owncloudHost, owncloudUsername, owncloudPassword);

    connector.printDirectoryContent("/");
    connector.createDirectory("/fromJavaClient");
    connector.createFile("/fromJavaClient/testTextFile.txt", "test content");
    connector.printDirectoryContent("/fromJavaClient");
    connector.delete("/fromJavaClient");
    connector.printDirectoryContent("/");
  }

  @Test
  public void nextcloudTest() throws IOException {
    SardineConnector connector = new SardineConnector(
        nextcloudHost, nextcloudUsername, nextcloudPassword);

    connector.printDirectoryContent("/");
    connector.createDirectory("/fromJavaClient");
    connector.createFile("/fromJavaClient/testTextFile.txt", "test content");
    connector.printDirectoryContent("/fromJavaClient");
    connector.delete("/fromJavaClient");
    connector.printDirectoryContent("/");
  }

}
