%Start              INITIAL1 HOUSENO RELATIONSHIP AGE AGE1 AGE2 AGE3 BIRTHPLACE PROFESSION INDUSTRY NEXT NEXT1 NEXT2 NEXT3 NEXT4
Number              [1-9][0-9]*
Word                [a-zA-Z]+

%{
    #include <stdio.h>
    #include <string.h>
    int row = 0;
    int months[13] = {};
    int tempNum = 0;
    int wife = 0;
    int wifeCount = 0;
    double houseNum = 0;
    int head = 0;
    int railroader = 0;
    double railroaders[75] = {};
    double nonRailroaders[75] = {};
    int railroaderIndex = 0;
    int nonRailroaderIndex = 0;
    void addLocation();
    
    struct location
    {
        char* name;
        int count;
    };
    
    struct location locations[75];
    int temp3a = 0;
    int temp3b = 0;
    int temp3c = 0;
    int check = 0;
%}

%%

<INITIAL>[^\n]*\n[^\n]*\n                                           {printf("START\n"); BEGIN INITIAL1;}
<INITIAL1>{Number},                                                 {wife = 0; head = 0; railroader = 0; houseNum = 0; BEGIN HOUSENO;}
<HOUSENO>{Number}(\.{Number})?                                      {houseNum = atof(yytext);}
<HOUSENO>,                                                          {BEGIN NEXT;}
<NEXT>[^\n,]*,[^\n,]*,[^\n,]*,[^\n,]*,                              {BEGIN RELATIONSHIP;}
<RELATIONSHIP>wife                                                  {wife = 1;}
<RELATIONSHIP>head                                                  {head = 1;}
<RELATIONSHIP>{Word}[-{Word}]*                                      {}
<RELATIONSHIP>,                                                     {BEGIN NEXT1;}
<NEXT1>[^\n,]*,[^\n,]*,[^\n,]*,[^\n,]*,                             {BEGIN AGE;}
<AGE>{Number}                                                       {tempNum = atoi(yytext); BEGIN AGE1;}
<AGE1>,                                                             {BEGIN NEXT2;}
<AGE1>-                                                             {BEGIN AGE2;}
<AGE1>\/{Number}                                                    {months[tempNum]++; BEGIN NEXT2;}
<AGE2>{Number}                                                      {tempNum = atoi(yytext); BEGIN AGE3;}
<AGE3>\/{Number}                                                    {months[tempNum]++;}
<AGE3>,                                                             {BEGIN NEXT2;}
<NEXT2>[^\n,]*,[^\n,]*,[^\n,]*,[^\n,]*,[^\n,]*,[^\n,]*,[^\n,]*,     {BEGIN BIRTHPLACE;}
<BIRTHPLACE>{Word}([ \t]+{Word})*                                   {BEGIN NEXT3;}
<NEXT3>,[^\n,]*,[^\n,]*,[^\n,]*,[^\n,]*,[^\n,]*,[^\n,]*,            {BEGIN PROFESSION;}
<PROFESSION>None,                                                   {BEGIN INDUSTRY;}
<PROFESSION>{Word}([ \t]{Word})*                                    {if(wife == 1){wifeCount++;};}
<PROFESSION>,                                                       {BEGIN INDUSTRY;}
<INDUSTRY>Railroad|Rairoad                                          {railroader = 1;}
<INDUSTRY>{Word}                                                    {}
<INDUSTRY>[ \t]                                                     {}
<INDUSTRY>\.                                                        {}
<INDUSTRY>,                                                         {BEGIN NEXT4;}
<NEXT4>[^\n,]*,[^\n,]*\n                                            {if((head == 1)&&(railroader == 1)&&(houseNum != 0)){railroaders[railroaderIndex] = houseNum; railroaderIndex++;}if((head == 1)&&(railroader == 0)&&(houseNum != 0)){nonRailroaders[nonRailroaderIndex] = houseNum; nonRailroaderIndex++;}; row++; BEGIN INITIAL1;}

%%
int main(){
    
    yyin = fopen("test.txt", "r");
    
    yylex();
    
    printf("\nPROBLEM 1\n------------\n");
    for(int i = 0; i < 75; i++){
        if(locations[i].name == NULL){
            break;
        }
        printf("There are %d people from %s.\n", locations[i].count, locations[i].name);
    }
    
    printf("\nPROBLEM 2\n------------\n");
    for(int i = 1; i < 13; i++){
        printf("There are %d children born in month: %d\n", months[i], (i));
    }
    
    int i = 0;
    while(railroaders[i] != 0){
        check = 0;
        int j = 0;
        while(nonRailroaders[j] != 0){
            if(fabs(railroaders[i]-nonRailroaders[j]) <= 4){
                check = 1;
            }
            j++;
        }
        if(check == 0){
            temp3a++;
        }
        i++;
    }
    
    i = 0;
    while(railroaders[i] != 0){
        check = 0;
        int j = 0;
        while(nonRailroaders[j] != 0){
            if(fabs(railroaders[i]-nonRailroaders[j]) <= 4){
                check = 1;
            }
            j++;
        }
        if(check == 1){
            temp3b++;
        }
        i++;
    }
    
    i = 0;
    while(railroaders[i] != 0){
        check = 0;
        for(int j = 0; j < 75; j++){
            if(i != j){
                if(fabs(railroaders[i] - railroaders[j] <= 4)){
                    check = 1;
                }
            }
        }
        if(check == 0){
            temp3c++;
        }
        i++;
    }
    
    
    printf("\nPROBLEM 3\n------------\n");
    printf("Number of households in which:\n\t1. Head of household is railroader and all neighbor heads of household are railroaders: %d\n", temp3a);
    printf("\t2. Head of household is railroader and some neighbor heads of household are not railroaders: %d\n", temp3b);
    printf("\t3. Head of household is railroader and no neighbor heads of household is a railroader: %d\n", temp3c);
    
    printf("\nPROBLEM 4\n------------\n");
    printf("There are %d wives who are employed.\n", wifeCount);
}

void addLocation(char* text){
    for(int i = 0; i < 75; i++){
        if(strncmp(locations[i].name, text, strlen(text)) == 0){
            locations[i].count++;
        }
        if(strncmp(locations[i].name, '\0', 2) == 0){
            locations[i].name = text;
            locations[i].count = 1;
            break;
        }
    }
}


