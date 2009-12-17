grammar ScribbleProtocol;

tokens {
	NAMESPACE = 'namespace';
	IMPORT = 'import';
	AS = 'as';
	PROTOCOL = 'protocol';
	IMPLEMENTS = 'implements';
	CONFORMS = 'conforms';
	TO = 'to';
	CHANNEL = 'channel';
	VIA = 'via';
	ROLE = 'role';
	FROM = 'from';
	TO = 'to';
	PLUS 	= '+' ;
	MINUS	= '-' ;
	MULT	= '*' ;
	DIV	= '/' ;
}

@header {
package org.scribble.protocol.parser.antlr;
}
   
@lexer::header {
package org.scribble.protocol.parser.antlr;
}
   
@members {
	public static void main(String[] args) throws Exception {
        ScribbleProtocolLexer lex = new ScribbleProtocolLexer(new ANTLRFileStream(args[0]));
       	CommonTokenStream tokens = new CommonTokenStream(lex);

		ScribbleProtocolParser parser = new ScribbleProtocolParser(tokens);

        try {
            parser.description();
        } catch (RecognitionException e)  {
            e.printStackTrace();
        }
    }
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

description: ( namespaceDeclaration )? ( importStatement )* protocolDefinition ;

namespaceDeclaration: NAMESPACE qualifiedName ';' ;

qualifiedName: ID ( '.' ID )* ;

importStatement: IMPORT qualifiedName ';' ;

protocolDefinition: PROTOCOL ID ( '@' ID )? '{' activityList '}' ;

activityList: ( activity )* ;

activity: interaction | roleList ;

roleList: ROLE roleDef ( ',' roleDef )* ';' ;

roleDef: ID ;

channelList: CHANNEL channelDef ( ',' channelDef )* ';' ;

channelDef: ID ( FROM ID )? ( TO ID )? ;

interaction: ( qualifiedName | ID '(' ')' ) ( FROM ID )? ( TO ID )? ( VIA ID )? ';' ;

expr	: term ( ( PLUS | MINUS )  term )* ;

term	: factor ( ( MULT | DIV ) factor )* ;

factor	: NUMBER ;


/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

ID : ('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

NUMBER	: (DIGIT)+ ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;

fragment DIGIT	: '0'..'9' ;
