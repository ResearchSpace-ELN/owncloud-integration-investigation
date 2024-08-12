curl 'https://owncloud-test.researchspace.com/remote.php/webdav/newFolderCreatedWithCurl' \
--user username:password \
--request MKCOL

curl 'https://owncloud-test.researchspace.com/remote.php/webdav/newFolderCreatedWithCurl' \
--user username:password \
--request DELETE
