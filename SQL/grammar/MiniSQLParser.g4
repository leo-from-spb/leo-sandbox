parser grammar MiniSQLParser;


options {
    tokenVocab=MiniSQLLexer;
}



query: querySection+
    ;

querySection
    : sectionWith
    | sectionSelect
    | sectionFrom
    | sectionWhere
    | sectionGroup
    | sectionHaving
    | sectionOrder
    ;

sectionWith: WITH;

sectionSelect: SELECT selectItem (COMMA selectItem)*;

sectionFrom: FROM relationship (COMMA relationship)*;

relationship
    : tableA (joinPhrase tableA joinCondition?)*
    ;

tableA
    : table (AS? alias=id)?
    ;

table
    : id
    | LP relationship RP
    ;

joinPhrase
    : LEFT OUTER? JOIN
    | INNER? JOIN
    | NATURAL JOIN
    | CROSS JOIN
    ;

joinCondition
    : expr
    ;


sectionWhere: WHERE bool;

sectionGroup: GROUP BY expr (COMMA expr)*;

sectionHaving: HAVING bool;

sectionOrder: ORDER BY orderItem (COMMA orderItem)*;

orderItem
    : expr
    ;

selectItem
    : ASTERIX
    | expr (AS? alias=id)?
    ;


literal
    : INT
    | FLOAT
    ;


//bool
//    : (TRUE | FALSE)
//    | LP bool RP
//    | NOT bool
//    | bool (AND bool)+
//    | bool (OR bool)+
//    | expr IS NOT? NULL
//    | id
//    ;

bool   : bool1 (OR bool1)*;
bool1  : bool2 (AND bool2)*;
bool2  : (TRUE | FALSE)
       | LP bool RP
       | NOT bool2
       | expr IS NOT? NULL
       | expr (LT|LE|GT|GE|EQ|NE) expr
       | expr BETWEEN expr AND expr
       | expr IN LP expr (COMMA expr)* RP
       | id
       ;


expr
    : literal
    | id
    | LP expr RP
    | expr (MULT | DIV) expr
    | expr (PLUS | MINUS) expr
    ;


id
    : PLAIN_ID
    | QUOTED_ID
    ;



