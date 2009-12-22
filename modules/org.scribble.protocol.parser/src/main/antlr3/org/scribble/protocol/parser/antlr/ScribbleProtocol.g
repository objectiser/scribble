grammar ScribbleProtocol;

options {
	output=AST;
}

tokens {
	INTERACTION = 'interaction' ;
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
	private org.scribble.core.logger.ScribbleLogger m_logger=null;
	
	public static void main(String[] args) throws Exception {
        ScribbleProtocolLexer lex = new ScribbleProtocolLexer(new ANTLRFileStream(args[0]));
       	CommonTokenStream tokens = new CommonTokenStream(lex);

		ScribbleProtocolParser parser = new ScribbleProtocolParser(tokens);

		ProtocolTreeAdaptor adaptor=new ProtocolTreeAdaptor();
		adaptor.setParser(parser);
		
		parser.setTreeAdaptor(adaptor);
		
        try {
            ScribbleProtocolParser.description_return r=parser.description();
            
            //CommonTree t=(CommonTree)r.getTree();
            
            //Tree t=(Tree)r.getTree();
            
            //System.out.println(t.toStringTree());
            
        } catch (RecognitionException e)  {
            e.printStackTrace();
        }
    }
    
    public void setLogger(org.scribble.core.logger.ScribbleLogger logger) {
    	m_logger = logger;
    }
    
    public void emitErrorMessage(String mesg) {
    	if (m_logger == null) {
    		super.emitErrorMessage(mesg);
    	} else {
    		m_logger.error(mesg, null);
    	}
    }
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

description: ( namespaceDeclaration )? ( importStatement )* protocolDef ;

namespaceDeclaration: 'namespace'^ qualifiedName ';'! ;

qualifiedName: ID ( '.' ID )* ;

qualifiedNameWithMeta: ID ( '.' ( '*' | qualifiedNameWithMeta ) )? ;

importStatement: 'import'^ qualifiedNameWithMeta ( 'as' ID )? ';'! ;

protocolDef: 'protocol'^ locatedNameDef sequenceDef ;

locatedNameDef: ID ( '@' participantName )? ;

sequenceDef: '{'! activityListDef '}'! ;

activityListDef: ( activityDef )* ;

activityDef: interactionDef | participantListDef ;

participantListDef: 'participant'^ participantDef ( ',' participantDef )* ';'! ;

participantDef: ID ;

channelListDef: 'channel'^ channelDef ( ',' channelDef )* ';'! ;

channelDef: ID ( 'from' ID )? ( 'to' ID )? ;

typeReferenceDef: qualifiedName ;

interactionSignatureDef: ( typeReferenceDef | ID '(' ')' ) ;

participantName: ID ;

interactionDef: interactionSignatureDef ( 'from' participantName )? ( 'to' participantName )? ( 'via' ID )? ';'! ;

choiceDef: 'choice'^ '@' participantName sequenceDef ( 'or' sequenceDef )* ;

repeatDef: 'repeat'^ '@' participantName sequenceDef ;

runDef: 'run'^ ( inlineProtocolDef | qualifiedName ( '@' ID )? ( boundParameters )? ';'! ) ;

inlineProtocolDef: 'protocol'^ ( boundParameters )? sequenceDef ;

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
