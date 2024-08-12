package com.researchspace.owncloud.investigation;

import com.github.sardine.DavResource;
import com.github.sardine.Sardine;
import com.github.sardine.SardineFactory;
import java.io.IOException;
import java.util.List;

public class SardineConnector {

  private final String hostname;
  private Sardine sardineClient;

  public SardineConnector(String hostname, String username, String password) {
    this.hostname = hostname;

    sardineClient = SardineFactory.begin();
    sardineClient.setCredentials(username, password);
  }

  public void printDirectoryContent(String directoryPath) throws IOException {

    List<DavResource> resources = sardineClient.list(hostname + directoryPath);
    for (DavResource res : resources)
    {
      System.out.println(res.getPath() + ", name: " + res.getName() + ", etag: " + res.getEtag() + ", modTime: " + res.getModified().getTime());
    }
    System.out.println("---");
  }

  public void createDirectory(String directoryPath) throws IOException {
    sardineClient.createDirectory(hostname + directoryPath);
  }

  public void createFile(String filePath, String content) throws IOException {
    sardineClient.put(hostname + filePath, content.getBytes());
  }

  public void delete(String path) throws IOException {
    sardineClient.delete(hostname + path);
  }



}
