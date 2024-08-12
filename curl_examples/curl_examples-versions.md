upload a file, and save a few versions:

curl 'https://owncloud-test.researchspace.com/remote.php/webdav/testFileVersions.txt' \
--user username:password \
--request PUT \
--data 'test content (initial).'

curl 'https://owncloud-test.researchspace.com/remote.php/webdav/testFileVersions.txt' \
--user username:password \
--request PUT \
--data 'test content (updated).'

curl 'https://owncloud-test.researchspace.com/remote.php/webdav/testFileVersions.txt' \
--user username:password \
--request PUT \
--data 'test content (updated again).'

retrieve versions - first find fileid
query for fileId:
curl 'https://owncloud-test.researchspace.com/remote.php/webdav/testFileVersions.txt' \
--user username:password \
--request PROPFIND \
--data '<?xml version="1.0" encoding="UTF-8"?>
<d:propfind xmlns:d="DAV:" xmlns:oc="http://owncloud.org/ns">
<d:prop>
<d:getcontenttype/>
<oc:fileid/>
<d:resourcetype/>
<d:getetag/>
</d:prop>
</d:propfind>'

now query versions api:
curl 'https://owncloud-test.researchspace.com/remote.php/dav/meta/154/v' \
--user username:password \
--request PROPFIND \
--data '<?xml version="1.0" encoding="UTF-8"?>
<d:propfind xmlns:d="DAV:" xmlns:oc="http://owncloud.org/ns" xmlns:nc="http://nextcloud.org/ns">
<d:prop>
<d:getlastmodified/>
<oc:meta-path-for-user/>
<oc:fileid/>
<d:getetag/></d:prop>
</d:propfind>'

response should show historical 3 versions:
<d:multistatus xmlns:d="DAV:" xmlns:s="http://sabredav.org/ns" xmlns:oc="http://owncloud.org/ns">
  <d:response>
    <d:href>/remote.php/dav/meta/154/v/</d:href>
    <d:propstat>
      <d:prop>
        <d:getlastmodified/>
        <oc:meta-path-for-user/>
        <oc:fileid/>
        <d:getetag/>
      </d:prop>
      <d:status>HTTP/1.1 404 Not Found</d:status>
    </d:propstat>
  </d:response>
  <d:response>
    <d:href>/remote.php/dav/meta/154/v/1723048403</d:href>
    <d:propstat>
      <d:prop>
        <d:getlastmodified>Wed, 07 Aug 2024 16:33:23 GMT</d:getlastmodified>
        <d:getetag>&quot;27e347423043c807a6e194a25729b4ca&quot;</d:getetag>
      </d:prop>
      <d:status>HTTP/1.1 200 OK</d:status>
    </d:propstat>
    <d:propstat>
      <d:prop>
        <oc:meta-path-for-user/>
        <oc:fileid/>
      </d:prop>
      <d:status>HTTP/1.1 404 Not Found</d:status>
    </d:propstat>
  </d:response>
  <d:response>
    <d:href>/remote.php/dav/meta/154/v/1723048397</d:href>
    <d:propstat>
      <d:prop>
        <d:getlastmodified>Wed, 07 Aug 2024 16:33:17 GMT</d:getlastmodified>
        <d:getetag>&quot;6f44c2ff39dc6e407e8178a7d1136975&quot;</d:getetag>
      </d:prop>
      <d:status>HTTP/1.1 200 OK</d:status>
    </d:propstat>
    <d:propstat>
      <d:prop>
        <oc:meta-path-for-user/>
        <oc:fileid/>
      </d:prop>
      <d:status>HTTP/1.1 404 Not Found</d:status>
    </d:propstat>
  </d:response>
</d:multistatus>