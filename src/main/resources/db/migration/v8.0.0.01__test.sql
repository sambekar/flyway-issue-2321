SET DEFINE OFF
INSERT INTO CONNECT_ENCODING.ACCESSRIGHT  (
    ID,
    CREATEDBY,
    CREATEDON,
    MODIFIEDBY,
    MODIFIEDON,
    DELETEDFLAG,
    CNTR,
    ACCESSRIGHTNAME,
    DESCRIPTION
  )
  VALUES
  (
    CONNECT_ENCODING.SQ_ACCESSRIGHT.nextval,
    'CR 106724',
    sysdate,
    'CR 106724',
    sysdate,
    0,
	0,
    'encodeAndPrintAll',
    'Allows user to access Encode & Print All button in Manual Bundle Encode Page'
  );
commit;
