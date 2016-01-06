
%{
    #include <stdio.h>
    #include <string.h>
    
    int yywrap(){
        return 1;
    }
    
    main(){
        yyparse();
    }
    
    int ctsNum;
    
    const char* town[19] = {"Benbush", "Brownsville", "Brushy Run", "Cabins", "Clarksburg", "Cumberland", "Flintstone", "Fort Seybert", "Franklin", "Gormania", "Harman", "Kearneysville", "Landes", "Petersburg", "Seoul", "Washington", "Wellsburg", "West Jefferson", "Winchester"};
    
    const char* county[19] = {"Tucker", "Cameron", "Pendleton", "Grant", "Harrison", "Allegany", "Allegany", "Pendleton", "Pendleton", "Grant", "Randolph", "Jefferson", "Grant", "Grant", "", "", "Brooks", "Madison", "Frederick"};
    
    const char* state[19] = {"West Virginia", "Texas", "West Virginia", "West Virginia", "West Virginia", "Maryland", "Maryland", "West Virginia", "West Virginia", "West Virginia", "West Virginia", "West Virginia", "West Virginia", "West Virginia", "South Korea", "D.C.", "West Virginia", "Ohio", "Virginia"};
    
    
%}

%token NAME DATEOFBIRTH NAMEOFTOWN NAMEOFCOUNTY NAMEOFSTATE DATEOFDEATH NAMEOFNEWSPAPER


%%