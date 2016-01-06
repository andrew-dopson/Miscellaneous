//
//  main.c
//  typeJustification
//
//  Created by Andrew Dopson on 10/16/13.
//  Copyright (c) 2013 Andrew Dopson. All rights reserved.
//

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

struct wordNode{
    char word[30];
    struct wordNode *next;
    int charNum;
};

void makeText();

int main(int argc, const char * argv[])
{
    struct wordNode *words;
    struct wordNode *first;

    words = malloc(sizeof(struct wordNode));
    first = malloc(sizeof(struct wordNode));
    
    char file_name[30];
    char c;
    
    FILE* fptr;
    int lineSize = 0;
    
    
    while(1){
        printf("Type in the name of the file containing your text document: ");
        scanf("%s", file_name);
    
        if ((fptr = fopen(file_name, "r")) == NULL){
            printf("File could not be opened.\n");
        }
        else{
            c = fgetc(fptr);
            while (!feof(fptr)) {
                int newPara = 0;
                while (isspace(c)){
                    if(strncmp(&c, "\n", 1) == 0){
                        newPara++;
                    }
                    c = fgetc(fptr);
                }
                int i = 0;
                char characters[30] = " ";
                while ((isalnum(c)) || (ispunct(c))){
                    characters[i] = c;
                    c = fgetc(fptr);
                    i++;
                }
                
                if(newPara > 1){
                    struct wordNode *newNode;
                    newNode = malloc(sizeof(struct wordNode));
                    newNode->charNum = 100;
                    words->next = newNode;
                    words = words->next;
                }
                
                if (first->charNum == 0) {
                    words->charNum = i;
                    strncpy(words->word, characters, i);
                    first = words;
                }else{
                    struct wordNode *newNode;
                    newNode = malloc(sizeof(struct wordNode));
                    newNode->charNum = i;
                    strncpy(newNode->word, characters, i);
                    words->next = newNode;
                    words = words->next;
                }
            }
            break;
        }
    }
    fclose(fptr);
    
    while (1) {
        printf("Please enter the number of characters you want in each line: ");
        scanf("%d", &lineSize);
        
        if ((lineSize >= 40) && (lineSize <= 100)) {
            break;
        }else{
            printf("Invalid number of characters.\n");
        }
    }
    
    struct wordNode *search;
    search = first;
    
    while (1){
        int numOfWords = 0;
        int charCount = 0;
        int lastLine = 0;
        int numOfSpaces, definiteSpaces, additionalSpace, whiteSpots;
        
        while (charCount + (numOfWords - 1) < lineSize) {
            if((charCount + search->charNum + (numOfWords)) <= lineSize){
                charCount += search->charNum;
                numOfWords++;
                search = search->next;
                if(search == NULL){
                    lastLine = 1;
                    break;
                }
                if(search->charNum == 100){
                    lastLine = 2;
                    search = search->next;
                    break;
                }
            }else{
                break;
            }
        }
        if(lastLine == 0){
            numOfSpaces = (lineSize - (charCount + (numOfWords - 1))) + (numOfWords - 1);
            definiteSpaces = numOfSpaces / (numOfWords - 1);
            additionalSpace = numOfSpaces % (numOfWords - 1);
            whiteSpots = numOfWords - 1;
        }
        if((lastLine == 1) || (lastLine == 2)){
            numOfSpaces = numOfWords - 1;
            definiteSpaces = 1;
            additionalSpace = 0;
            whiteSpots = numOfSpaces;
        }
        struct wordNode *garbage;
        garbage = first;
        while (first != search) {
            if(first->charNum == 100){
                printf("\n");
            }
            printf("%s", first->word);
            if(whiteSpots != 0){
                for(int i = 0; i < definiteSpaces; i++){
                    printf(" ");
                }
                whiteSpots--;
            }
            if(additionalSpace != 0){
                printf(" ");
                additionalSpace--;
            }
            first = first->next;
            free(garbage);
            garbage = first;
        }
        printf("\n");
        if(lastLine == 1){break;}
    }
    
}
















