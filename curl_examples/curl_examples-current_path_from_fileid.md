curl 'https://owncloud-test.researchspace.com/remote.php/webdav' \
--user username:password \
--request PROPFIND \
--data '<?xml version="1.0" encoding="UTF-8"?>
<d:propfind xmlns:d="DAV:" xmlns:oc="http://owncloud.org/ns" xmlns:nc="http://nextcloud.org/ns">
<d:prop>
<d:getlastmodified/>
<d:getcontentlength/>
<d:getcontenttype/>
<oc:fileid/>
<d:resourcetype/>
<d:getetag/>
</d:prop>
</d:propfind>'

response (limited to fragments showing file and folder):
<d:multistatus xmlns:d="DAV:" xmlns:s="http://sabredav.org/ns" xmlns:oc="http://owncloud.org/ns">
  ...
  <d:response>
    <d:href>/remote.php/webdav/Photos/</d:href>
    <d:propstat>
      <d:prop>
        <d:getlastmodified>Mon, 05 Jul 2021 16:05:16 GMT</d:getlastmodified>
        <oc:fileid>40</oc:fileid>
        <d:resourcetype>
          <d:collection/>
        </d:resourcetype>
      </d:prop>
      <d:status>HTTP/1.1 200 OK</d:status>
    </d:propstat>
    <d:propstat>
      <d:prop>
        <d:getcontentlength/>
        <d:getcontenttype/>
      </d:prop>
      <d:status>HTTP/1.1 404 Not Found</d:status>
    </d:propstat>
  </d:response>
  <d:response>
    <d:href>/remote.php/webdav/storage_unit_locations.png</d:href>
    <d:propstat>
      <d:prop>
        <d:getlastmodified>Wed, 25 Nov 2020 15:20:50 GMT</d:getlastmodified>
        <d:getcontentlength>168434</d:getcontentlength>
        <d:getcontenttype>image/png</d:getcontenttype>
        <oc:fileid>67</oc:fileid>
        <d:resourcetype/>
      </d:prop>
      <d:status>HTTP/1.1 200 OK</d:status>
    </d:propstat>
  </d:response>
  ...
</d:multistatus>

then query for metadata of the file by fileid (note 'dav' rather than 'webdav' endpoint):
curl 'https://owncloud-test.researchspace.com/remote.php/dav/meta/67' \
--user username:password \
--request PROPFIND \
--data '<?xml version="1.0" encoding="UTF-8"?>
<d:propfind xmlns:d="DAV:" xmlns:oc="http://owncloud.org/ns" xmlns:nc="http://nextcloud.org/ns">
<d:prop>
<d:getlastmodified/>
<oc:meta-path-for-user/>
<oc:fileid/>
</d:prop>
</d:propfind>'

response (limited to last-version details)
<d:multistatus xmlns:d="DAV:" xmlns:s="http://sabredav.org/ns" xmlns:oc="http://owncloud.org/ns">
  <d:response>
    <d:href>/remote.php/dav/meta/67/</d:href>
    <d:propstat>
      <d:prop>
        <oc:meta-path-for-user>/storage_unit_locations.png</oc:meta-path-for-user>
      </d:prop>
      <d:status>HTTP/1.1 200 OK</d:status>
    </d:propstat>
    <d:propstat>
      <d:prop>
        <d:getlastmodified/>
        <oc:fileid/>
      </d:prop>
      <d:status>HTTP/1.1 404 Not Found</d:status>
    </d:propstat>
  </d:response>
  ...
</d:multistatus>

response after moving the file (with UI) inside 'Documents' dir:
<d:multistatus xmlns:d="DAV:" xmlns:s="http://sabredav.org/ns" xmlns:oc="http://owncloud.org/ns">
  <d:response>
    <d:href>/remote.php/dav/meta/67/</d:href>
    <d:propstat>
      <d:prop>
        <oc:meta-path-for-user>/Documents/storage_unit_locations.png</oc:meta-path-for-user>
      </d:prop>
      <d:status>HTTP/1.1 200 OK</d:status>
    </d:propstat>
    <d:propstat>
      <d:prop>
        <d:getlastmodified/>
        <oc:fileid/>
      </d:prop>
      <d:status>HTTP/1.1 404 Not Found</d:status>
    </d:propstat>
  </d:response>
  ...
</d:multistatus>