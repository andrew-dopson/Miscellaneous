//
//  main.c
//  pathfinder
//
//  Created by Andrew Dopson on 8/30/13.
//  Copyright (c) 2013 Andrew Dopson. All rights reserved.
//

/* Programming Assignment 1
   CS 350                   */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

/* Global variables used for keeping track of which way 
   the tracer is facing */
#define NORTH 0
#define EAST 1
#define SOUTH 2
#define WEST 3

int r, c, startX, startY, endX, endY;

void fieldGenerator();
void printField();
void findStartEnd();
void solveMaze();

int main()
{
    /* Asks the user to enter the number of rows and columns */
    printf("Please enter the number of rows you would like: ");
    scanf("%d", &r);
    printf("Please enter the number of columns you would like: ");
    scanf("%d", &c);
    
    char maze[r][c];
    
    /* This section of code reads in an input file that contains
       a given maze */
    int i, j;
    FILE *fptr;
    char ch;
    char file_name[20];
    
    printf("Type in the name of the file containing the Field: ");
    scanf("%s",file_name);
    fptr=fopen(file_name,"r");
    for (i=0; i<r; i++)
        for (j=0; j<c; j++){
            ch=fgetc(fptr);
            while ( !((ch == '1')||(ch=='0')) ) ch=fgetc(fptr);
            maze[i][j]=ch;
        }
    fclose(fptr);
    
    for (i=0; i<r; i++)
        for (j=0; j<c; j++)  {
            if (j == 0) printf("\n");
            printf("%c ",maze[i][j]);
        }
    printf("\n");

    
    //fieldGenerator(maze);
    //printField(maze);
    
    findStartEnd(maze);
    solveMaze(maze);
    
}

/* Prints the maze in its current form */
void printField(const char maze[][c])
{
    int x, y;
    
    for (x = 0; x < r; x++) {
        
        for (y = 0; y < c; y++)
            printf("%c ", maze[x][y]);
        
        printf("\n");
    }
    printf("\n");
}

/* Generates a random maze based on the given number 
   of rows and columns. (Used for testing) */
void fieldGenerator(char maze[][c])
{
    int a, x, y, entry, exit, loop;
    
    
    srandom(time(NULL));
    
    do {
        entry = random() % 4;
        exit = random() % 4;
    } while (entry == exit);
    
    /* Determine entry position */
    if (entry == 0) {
        x = 1 + random() % (r-2);    /* avoid corners */
        y = 0;
        maze[x][0] = '0';
    }
    else if (entry == 1) {
        x = 0;
        y = 1 + random() % (c-2);
        maze[0][y] = '0';
    }
    else if (entry == 2) {
        x = 1 + random() % (r-2);
        y = c-1;
        maze[x][y] = '0';
    }
    else {
        x = r-1;
        y = 1 + random() % (c-2);
        maze[x][y] = '0';
    }
    
    /* Determine exit location */
    
    if (exit == 0) {
        a = 1 + random() % (r-2);
        maze[a][0] = '0';
    }
    else if (exit == 1) {
        a = 1 + random() % (c-2);
        maze[0][a] = '0';
    }
    else if (exit == 2) {
        a = 1 + random() % (r-2);
        maze[a][c-1] = '0';
    }
    else {
        a = 1 + random() % (c-2);
        maze[r-1][a] = '0';
    }
    
    for (loop = 1; loop < (c*c)*2/3; loop++) {   /* add zeroes */
        x = 1 + random() % (r-2);
        y = 1 + random() % (c-2);
        maze[x][y] = '0';
    }
}

/* Finds the starting and ending points of the maze. Checks each 
   side of the maze until it finds a zero.  Once the first zero is
   found the 'start' variable is increment. The program then begins
   looking for the next zero.  Once that is found, the program exits
   the method */
void findStartEnd(char maze[][c])
{
    /* Variable that checks if any 0's have been found */
    int start = 0;
    
    /* Searches left side of maze */
    for(int i = 0; i < r; i++){
        if(start > 1){
            return;
        }
        if(maze[i][0] == '0'){
            if(start == 0){
                startX = i;
                startY = 0;
            }
            if(start == 1){
                endX = i;
                endY = 0;
                return;
            }
            start++;
        }
    }
    /* Searches bottom side of maze */
    for(int i = 0; i < c; i++){
        if(start > 1){
            return;
        }
        if(maze[r-1][i] == '0'){
            if(start == 0){
                startX = r-1;
                startY = i;
                            }
            if(start == 1){
                endX = r-1;
                endY = i;
                return;
            }
            start++;
        }
    }
    /* Searches right side of maze */
    for(int i = r-1; i >= 0; i--){
        if(start > 1){
            return;
        }
        if(maze[i][c-1] == '0'){
            if(start == 0){
                startX = i;
                startY = c-1;
            }
            if(start == 1){
                endX = i;
                endY = c-1;
                return;
            }
            start++;
        }
    }
    /* Searches top side of maze */
    for(int i = c-1; i >= 0; i--){
        if(start > 1){
            return;
        }
        if(maze[0][i] == '0'){
            if(start == 0){
                startX = 0;
                startY = i;
            }
            if(start == 1){
                endX = 0;
                endY = i;
                return;
            }
            start++;
        }
    }

    
}

/* Moves through maze keeping wall on right hand side
 at all times.  Marks an X in every spot the tracer 
 moves into */
void solveMaze(char maze[][c])
{
    /* Initializes starting position of tracer */
    int tracerX = startX;
    int tracerY = startY;
    
    /* Directional variable that keeps track of which
     direction the tracer is facing in order to keep track of
     its right hand side */
    int direction;
    
    /* Determines initial direction of the tracer based on 
       the location of the starting zero */
    if(startX == 0){
        direction = SOUTH;
    }else if (startX == (r-1)){
        direction = NORTH;
    }else if (startY == 0){
        direction = EAST;
    }else{
        direction = WEST;
    }
    
    /* Gives beginning position an 'X' */
    maze[tracerX][tracerY] = 'X';
    
    /* This moves the tracer in from the outter walls of the maze depending on which
       side the tracer starts on */
    if(direction == SOUTH){
        if(maze[tracerX + 1][tracerY] == '0'){
            tracerX++;
            maze[tracerX][tracerY] = 'X';
        }else{
            printField(maze);
            printf("No path found.");
            return;
        }
    }else if (direction == NORTH){
        if(maze[tracerX - 1][tracerY] == '0'){
            tracerX--;
            maze[tracerX][tracerY] = 'X';
        }else{
            printField(maze);
            printf("No path found.");
            return;
        }
    }else if (direction == EAST){
        if(maze[tracerX][tracerY + 1] == '0'){
            tracerY++;
            maze[tracerX][tracerY] = 'X';
        }else{
            printField(maze);
            printf("No path found.");
            return;
        }
    }else{
        if(maze[tracerX][tracerY - 1] == '0'){
            tracerY--;
            maze[tracerX][tracerY] = 'X';
        }else{
            printField(maze);
            printf("No path found.");
            return;
        }
    }
    
    /* While loop that continues executing until the tracer reaches 
     the end or back to the beginning */
    while(1){
        /* If the current direction is North, the tracer first looks to the right of that position
           (East). If that position contains a zero, the tracer will move to that position and mark
           an 'X' in that position. If it does not contain a zero, the tracer then looks to the 
           position in front of it (North) and does the same thing. If it doesn't move to that 
           position it then looks to the left and performs the same comparison. If that position 
           also doesn't contain a zero, the tracer turns around and goes back to the previous 
           position. */
        if(direction == NORTH){
            if((maze[tracerX][tracerY + 1] == '0') || (maze[tracerX][tracerY + 1] == 'X')){
                tracerY++;
                maze[tracerX][tracerY] = 'X';
                direction = EAST;
            }else if ((maze[tracerX - 1][tracerY] == '0') || (maze[tracerX - 1][tracerY] == 'X')){
                tracerX--;
                maze[tracerX][tracerY] = 'X';
            }else if ((maze[tracerX][tracerY - 1] == '0') || (maze[tracerX][tracerY - 1] == 'X')){
                tracerY--;
                maze[tracerX][tracerY] = 'X';
                direction = WEST;
            }else{
                tracerX++;
                maze[tracerX][tracerY] = 'X';
                direction = SOUTH;
            }
            /* Same as previous */
        }else if(direction == SOUTH){
            if((maze[tracerX][tracerY - 1] == '0') || (maze[tracerX][tracerY - 1] == 'X')){
                tracerY--;
                maze[tracerX][tracerY] = 'X';
                direction = WEST;
            }else if ((maze[tracerX + 1][tracerY] == '0') || (maze[tracerX + 1][tracerY] == 'X')){
                tracerX++;
                maze[tracerX][tracerY] = 'X';
            }else if ((maze[tracerX][tracerY + 1] == '0') || (maze[tracerX][tracerY + 1] == 'X')){
                tracerY++;
                maze[tracerX][tracerY] = 'X';
                direction = EAST;
            }else{
                tracerX--;
                maze[tracerX][tracerY] = 'X';
                direction = NORTH;
            }
            /* Same as previous */
        }else if(direction == EAST){
            if((maze[tracerX + 1][tracerY] == '0') || (maze[tracerX + 1][tracerY] == 'X')){
                tracerX++;
                maze[tracerX][tracerY] = 'X';
                direction = SOUTH;
            }else if ((maze[tracerX][tracerY + 1] == '0') || (maze[tracerX][tracerY + 1] == 'X')){
                tracerY++;
                maze[tracerX][tracerY] = 'X';
            }else if ((maze[tracerX - 1][tracerY] == '0') || (maze[tracerX - 1][tracerY] == 'X')){
                tracerX--;
                maze[tracerX][tracerY] = 'X';
                direction = NORTH;
            }else{
                tracerY--;
                maze[tracerX][tracerY] = 'X';
                direction = WEST;
            }
            /* Same as previous */
        }else{
            if((maze[tracerX - 1][tracerY] == '0') || (maze[tracerX - 1][tracerY] == 'X')){
                tracerX--;
                maze[tracerX][tracerY] = 'X';
                direction = NORTH;
            }else if ((maze[tracerX][tracerY - 1] == '0') || (maze[tracerX][tracerY - 1] == 'X')){
                tracerY--;
                maze[tracerX][tracerY] = 'X';
            }else if ((maze[tracerX + 1][tracerY] == '0') || (maze[tracerX + 1][tracerY] == 'X')){
                tracerX++;
                maze[tracerX][tracerY] = 'X';
                direction = SOUTH;
            }else{
                tracerY++;
                maze[tracerX][tracerY] = 'X';
                direction = EAST;
            }

        }
        /* If the tracer reaches the end position, "Path found!" is printed out as well as
           the resolved maze and the method is exited */
        if((tracerX == endX) && (tracerY == endY)){
            printf("Path found!\n");
            printField(maze);
            return;
        }
        
        /* If the tracer reaches the beginning of the maze, "Path not found" is printed as well
           as the unresolved maze and the method is exited */
        if((tracerX == startX) && (tracerY == startY)){
            printf("Path not found.\n");
            printField(maze);
            return;
        }
    }
    
    
    
    
    
    
    
    
    
}
