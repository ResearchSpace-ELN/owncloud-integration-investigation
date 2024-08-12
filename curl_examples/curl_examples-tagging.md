upload file:
curl 'https://owncloud-test.researchspace.com/remote.php/webdav/taggingTestFile.txt' \
--user username:password \
--request PUT \
--data 'tagging test content (text).'

tag (nextcloud way):
curl 'https://owncloud-test.researchspace.com/remote.php/webdav/taggingTestFile.txt' \
--user username:password \
--request PROPPATCH \
--data '<?xml version="1.0"?>
<d:propertyupdate xmlns:d="DAV:" xmlns:oc="http://owncloud.org/ns">
<d:set>
<d:prop>
<oc:tags><oc:tag>curlTestTag1</oc:tag></oc:tags>
</d:prop>
</d:set>
</d:propertyupdate>'

owncloud way - system tags ("collaborative tags"?):

owncloud - list all system tags:
curl 'https://owncloud-test.researchspace.com/remote.php/dav/systemtags' \
--user username:password \
--request PROPFIND \
--data '<?xml version="1.0" encoding="UTF-8"?>
<d:propfind xmlns:d="DAV:" xmlns:oc="http://owncloud.org/ns" xmlns:nc="http://nextcloud.org/ns">
<d:prop>
<oc:display-name/>
<oc:user-visible/>
<oc:user-assignable/>
<oc:id/>
</d:prop>
</d:propfind>'

owncloud - add system tag (note admin user details!)
curl 'https://owncloud-test.researchspace.com/remote.php/dav/systemtags' \
--user username:password \
--request POST \
--header "Content-Type: application/json" \
--data-binary '{"name":"curlTest","userVisible":"true","userAssignable":"true"}'


delete file:

curl 'https://owncloud-test.researchspace.com/remote.php/webdav/taggingTestFile.txt' \
--user username:password \
--request DELETE
