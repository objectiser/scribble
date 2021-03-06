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
	FULLSTOP = '.' ;
}

@header {
package org.scribble.protocol.parser.antlr;
}
   
@lexer::header {
package org.scribble.protocol.parser.antlr;
}
   
@members {
	private org.scribble.common.logging.Journal m_journal=null;
	
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
    
    public void setJournal(org.scribble.common.logging.Journal journal) {
    	m_journal = journal;
    }
    
    public void emitErrorMessage(String mesg) {
    	if (m_journal == null) {
    		super.emitErrorMessage(mesg);
    	} else {
    		m_journal.error(mesg, null);
    	}
    }
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

description: ( namespaceDeclaration )? ( importStatement )* protocolDef ;

namespaceDeclaration: 'namespace'^ qualifiedName ';'! ;

qualifiedName: ID ( '.' ID )* ;

urlDef: URL ;

importStatement: 'import'^ qualifiedName ( '@' urlDef )? ';'! ;

protocolDef: 'protocol'^ locatedNameDef blockDef ;

locatedNameDef: ID ( '@' participantName )? ;

blockDef: sequenceDef ;

sequenceDef: '{'! activityListDef '}'! ;

activityListDef: ( activityDef )* ;

activityDef: participantListDef | interactionDef | 
			raiseDef  | choiceDef | parallelDef | optionalDef | repeatDef | runDef | 
			tryEscapeDef | protocolDef ;

participantListDef: 'participant'^ participantDef ( ','! participantDef )* ';'! ;

participantDef: ID ;

participantName: ID ;

typeReferenceDef: qualifiedName ;

interactionSignatureDef: ( typeReferenceDef | ID '('! typeReferenceDef ( ','! typeReferenceDef )* ')'! ) ;

interactionDef: interactionSignatureDef ( 'from' participantName )? ( 'to' participantName )? ';'! ;

choiceDef: 'choice'^ ( 'from' participantName )? ( 'to' participantName )? '{'! ( whenBlockDef )+ '}'! ;

whenBlockDef: 'when'^ interactionSignatureDef sequenceDef ;

repeatDef: 'repeat'^ ( '@' participantName ( ','! participantName )* )? blockDef ;

optionalDef: 'optional'^ ( '@' participantName ( ','! participantName )* )? blockDef ;

runDef: 'run'^ ( ( ( '@' participantName )? '('! boundParameter ( ','! boundParameter )* ')'! )? inlineProtocolDef | 
			protocolRefDef ( '('! boundParameter ( ','! boundParameter )* ')'! )? ';'! ) ;

protocolRefDef: ID ( '@' participantName )? ;

inlineProtocolDef: blockDef ;

declarationName: ID ;

boundParameter: declarationName '=' declarationName ;

parallelDef: 'parallel'^ blockDef ( 'and' blockDef )* ;

raiseDef: 'raise'^ '@' participantName ( ','! participantName )* typeReferenceDef ';'! ;

tryEscapeDef: 'try'^ blockDef ( catchOrInterruptBlockDef )+ ;

catchOrInterruptBlockDef: catchBlockDef | interruptBlockDef ;

catchBlockDef: 'catch'^ ( '@' participantName ( ',' participantName )* )? typeReferenceDef sequenceDef ;

interruptBlockDef: 'interrupt'^ ( '@' participantName ( ',' participantName )* )? sequenceDef ;


/*-----------------------------------------------
TO DO:
Declaration (variables) possibly - but that may need
lookahead to avoid conflict with interactions.
-------------------------------------------------*/



expr	: term ( ( PLUS | MINUS )  term )* ;

term	: factor ( ( MULT | DIV ) factor )* ;

factor	: NUMBER ;


/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

ID : ('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'0'..'9'|'_')* ;

URL : (ID|':'|'?'|'/')+ ;

NUMBER	: (DIGIT)+ ;

WHITESPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' )+ 	{ $channel = HIDDEN; } ;

fragment DIGIT	: '0'..'9' ;
