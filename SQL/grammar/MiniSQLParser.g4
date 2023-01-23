parser grammar MiniSQLParser;


options {
    tokenVocab=MiniSQLLexer;
}



query		: querySection+ EOF
		;

querySection	: sectionWith
		| sectionSelect
		| sectionFrom
		| sectionWhere
		| sectionGroup
		| sectionHaving
		| sectionOrder
		;

sectionWith	: WITH
		;

sectionSelect	: SELECT selectItem (COMMA selectItem)*
		;

sectionFrom	: FROM relationship (COMMA relationship)*
		;

relationship	: tableA (joinPhrase tableA joinCondition?)*
		;

tableA		: table (AS? alias=id)?
		;

table		: thing
		| LP relationship RP
		;

joinPhrase	: LEFT OUTER? JOIN
		| INNER? JOIN
		| NATURAL JOIN
		| CROSS JOIN
		;

joinCondition	: expr
		;


sectionWhere	: WHERE bool
		;

sectionGroup	: GROUP BY expr (COMMA expr)*
		;

sectionHaving	: HAVING bool
		;

sectionOrder	: ORDER BY orderItem (COMMA orderItem)*
		;

orderItem	: expr
		;

selectItem	: STAR
		| expr (AS? alias=id)?
		;


literal		: INT
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


bool    : bool1 (OR bool1)*                     #BoolOr
        ;
bool1   : bool2 (AND bool2)*                    #BoolAnd
        ;
bool2   : (TRUE | FALSE)                        #BoolLiteral
        | cortege (EQ|NE) cortege               #BoolCompareCortegen
        | LP bool RP                            #BoolParentheses
        | NOT bool2                             #BoolNot
        | expr IS NOT? NULL                     #BoolNullability
        | expr (LT|LE|GT|GE|EQ|NE) expr         #BoolComparison
        | expr BETWEEN expr AND expr            #BoolBetween
        | expr IN LP expr (COMMA expr)* RP      #BoolIn
        | thing                                 #BoolId
        ;


cortege : LP expr (COMMA expr)* RP
        ;


expr    : expr1 ((PLUS | MINUS) expr1)*         #ExprPlusMinus
        ;
expr1   : expr2 ((STAR | DIV) expr2)*           #ExprMultDiv
        ;
expr2   : literal                               #ExprLiteral
        | LP expr RP                            #ExprParentheses
        | thing LP parameters=funParameters? RP #ExprCallFun
        | thing                                 #ExprThing
        ;


funParameters   : funParameter (COMMA funParameter)*
                ;

funParameter    : expr                       	#FunParameterSimple
                | id EQTO expr               	#FunParameterNamed
                ;

thing   : id (DOT id)*
        ;

id      : PLAIN_ID                           	#IdPlain
        | QUOTED_ID                          	#IdQuoted
        ;



