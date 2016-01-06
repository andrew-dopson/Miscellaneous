

Number              [1-9][0-9]*
Word                [a-zA-Z]+

%{
    #include <stdio.h>
    #include "y.tab.c"
 
%}
%%



%%

int main(){
    yyin = fopen("test.txt", "r");
    yylex();
}