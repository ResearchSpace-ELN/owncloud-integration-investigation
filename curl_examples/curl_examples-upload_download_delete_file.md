upload:

curl 'https://owncloud-test.researchspace.com/remote.php/webdav/testFile.txt' \
--user username:password \
--request PUT \
--data 'test content (text updated).'

download:

curl 'https://owncloud-test.researchspace.com/remote.php/webdav/testFile.txt' \
--user username:password \
--request GET

query for fileId
curl 'https://owncloud-test.researchspace.com/remote.php/webdav/testFile.txt' \
--user username:password \
--request PROPFIND \
--data '<?xml version="1.0" encoding="UTF-8"?>
<d:propfind xmlns:d="DAV:" xmlns:oc="http://owncloud.org/ns">
<d:prop>
<d:getcontenttype/>
<oc:fileid/>
<d:resourcetype/>
</d:prop>
</d:propfind>'

tag (possibly just owncloud), note dav endpoint & request by fileid:

curl 'https://owncloud-test.researchspace.com/remote.php/dav/systemtags-relations/files/88' \
--user username:password \
--request PROPFIND

delete:

curl 'https://owncloud-test.researchspace.com/remote.php/webdav/testFile.txt' \
--user username:password \
--request DELETE
