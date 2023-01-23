lexer grammar MiniSQLLexer;

options {
    caseInsensitive = true;
}



AND             : 'AND';
AS              : 'AS';
IS              : 'IS';
BETWEEN         : 'BETWEEN';
BY              : 'BY';
CROSS           : 'CROSS';
FROM            : 'FROM';
FALSE           : 'FALSE';
GROUP           : 'GROUP';
HAVING          : 'HAVING';
IN              : 'IN';
INNER           : 'INNER';
JOIN            : 'JOIN';
LEFT            : 'LEFT';
NATURAL         : 'NATURAL';
NOT             : 'NOT';
NULL            : 'NULL';
OR              : 'OR';
ORDER           : 'ORDER';
OUT             : 'OUT';
OUTER           : 'OUTER';
RETURN          : 'RETURN';
SELECT          : 'SELECT';
WHERE           : 'WHERE';
TRUE            : 'TRUE';
WITH            : 'WITH';


INT         : [+-]? [0-9]+;
FLOAT       : [+-]? [0-9]+ [.] [0-9]*;

PLAIN_ID    : [A-Z] [A-Z0-9$#_]*;
QUOTED_ID   : '"' .*? '"';


STAR        : '*';
COMMA       : ',';
DOT         : '.';
LP          : '(';
RP          : ')';

EQTO        : '=>';

LT          : '<';
LE          : '<=';
GT          : '>';
GE          : '>=';
EQ          : '=';
NE          : '!=' | '<>';


PLUS        : '+';
MINUS       : '-';
DIV         : '/';



WHITESPACE  : [ \t\r\n\u000C]+ -> channel(HIDDEN);
COMMENT     : '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT: '--' ~[\r\n]*    -> channel(HIDDEN);
