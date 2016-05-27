CREATE OR replace DIRECTORY SQL  AS 'E:\Computer Science Faculty\An II Sem II\SGBD\Project\SQL';
/
CREATE TABLE xmlTest (
  x XMLType
  );
/
INSERT INTO xmlTest values(XMLtype('<name>gfd</name>'));
/
select extractValue(x,'/ROW/name') from xmlTest;
/
CREATE OR REPLACE PROCEDURE add_xml_data(myFile varchar2) IS
BEGIN
  INSERT INTO user_history(id_history,id_user,departure_id,arrival_id,count)
  WITH t AS (SELECT xmltype(bfilename('SQL',myFile), nls_charset_id('WE8ISO8859P1')) xmlcol FROM dual)
  SELECT
  extractValue(value(x),'/ROW/id_history') id_history      
  ,extractValue(value(x),'/ROW/id_user') id_user
  ,extractValue(value(x),'ROW/departure_id') departure_id
  ,extractValue(value(x),'ROW/arrival_id') arrival_id
  ,extractValue(value(x),'ROW/count') count
  FROM t,TABLE(XMLSequence(extract(t.xmlcol,'/PERSON/ROW'))) x;
END;
/