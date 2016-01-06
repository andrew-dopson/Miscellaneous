

Name [A-Z][a-z]+[ ][A-Z][a-z]+
Word [a-z]+
Wordd [A-Za-z\(\)\.,']+
Space [ \t]

%{
    #include <stdio.h>
    #include <string.h>
    
    const char* bodyPart[6] = {"back", "ankle", "hamstring", "concussion", "shoulder", "knee"};
    
    const char* teams[32] = {"Buffalo Bills", "Miami Dolphins", "New England Patriots", "New York Jets", "Baltimore Ravens", "Cincinnati Bengals", "Cleveland Browns", "Pittsburgh Steelers", "Houston Texans", "Indianapolis Colts", "Jacksonville Jaguars", "Tennessee Titans", "Denver Broncos", "Kansas City Chiefs", "Oakland Raiders", "San Diego Chargers", "Dallas Cowboys", "New York Giants", "Philadelphia Eagles", "Washington Redskins", "Chicago Bears", "Detroit Lions", "Green Bay Packers", "Minnesota Vikings", "Atlanta Falcons", "Carolina Panthers", "New Orleans Saints", "Tampa Bay Buccaneers", "Arizona Cardinals", "St. Louis Rams", "San Francisco 49ers", "Seattle Seahawks"};
    
    const char* name;
    int compareLimit = 20;
    const char* injury;
    
    int team = 0;
    
%}

%%

{Name}              {for(int i = 0; i < 32; i++){if(strncmp(teams[i],yytext, compareLimit) == 0){printf("Team: %s\n", teams[i]); team = 1;}}; if(team == 0){printf("Player: %s\n", yytext);}; team = 0;}

{Word}              {for(int i = 0; i < 32; i++){if(strncmp(bodyPart[i], yytext, compareLimit) == 0){printf("Suffering from a %s injury.\n", bodyPart[i]);}};}
{Wordd}             {}
{Space}             {}
       
%%

int main(){
    yyin = fopen("/Users/adopson/Documents/cs410test2.txt", "r");
    yylex();
}