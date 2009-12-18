grammar ScribbleProtocol;

tokens {
	PLUS 	= '+' ;
	MINUS	= '-' ;
	MULT	= '*' ;
	DIV	= '/' ;
}

/* Problem is that when tokens are defined, it uses the token name,
   rather than the value, in the error messages.
   
	NAMESPACE = 'namespace';
	IMPORT = 'import';
	AS = 'as';
	IMPLEMENTS = 'implements';
	CONFORMS = 'conforms';
	TO = 'to';
	CHANNEL = 'channel';
	VIA = 'via';
	ROLE = 'role';
	FROM = 'from';
	TO = 'to';
*/

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

description: ( namespaceDeclaration )? ( importStatement )* protocolDef ;

namespaceDeclaration: 'namespace' qualifiedName ';' ;

qualifiedName: ID ( '.' ID )* ;

qualifiedNameWithMeta: ID ( '.' ( '*' | qualifiedNameWithMeta ) )? ;

importStatement: 'import' qualifiedNameWithMeta ( 'as' ID )? ';' ;

protocolDef: 'protocol' ID ( '@' ID )? sequenceDef ;

sequenceDef: '{' activityListDef '}' ;

activityListDef: ( activityDef )* ;

activityDef: interactionDef | roleListDef ;

roleListDef: 'role' roleDef ( ',' roleDef )* ';' ;

roleDef: ID ;

channelListDef: 'channel' channelDef ( ',' channelDef )* ';' ;

channelDef: ID ( 'from' ID )? ( 'to' ID )? ;

interactionDef: ( qualifiedName | ID '(' ')' ) ( 'from' ID )? ( 'to' ID )? ( 'via' ID )? ';' ;

choiceDef: 'choice' '@' ID sequenceDef ( 'or' sequenceDef )* ;

repeatDef: 'repeat' '@' ID sequenceDef ;

runDef: 'run' ( inlineProtocolDef | qualifiedName ( '@' ID )? ( boundParameters )? ';' ) ;

inlineProtocolDef: 'protocol' ( boundParameters )? sequenceDef ;

boundParameters: '(' boundParameter ( ',' boundParameter )* ')' ;

boundParameter: ID 'for' ID ;




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
