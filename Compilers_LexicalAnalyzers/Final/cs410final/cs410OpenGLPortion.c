//
//  main.c
//  finalProjectCS410
//
//  Created by Andrew Dopson on 11/27/13.
//  Copyright (c) 2013 Andrew Dopson. All rights reserved.
//

#include <GLUT/GLUT.h>
#include <stdio.h>

void mydisplay();
void createHuman();
void createLeftLeg();
void createRightLeg();
int lookFromPosX = 40.0;
int lookFromPosY = 7.0;
int lookFromPosZ = 90.0;
int lookAtPosX = 40.0;
int lookAtPosY = 4.0;
int lookAtPosZ = 80.0;
int humanPosX = 40.0;
int humanPosZ = 80.0;
const char* injury = "knee";


int main(int argc, char** argv){
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_DEPTH | GLUT_RGB);
    glutInitWindowSize(500, 500);
    glutInitWindowPosition(0, 0);
    glutCreateWindow("Final Project");
    glEnable(GL_DEPTH_TEST);
    glEnable(GL_NORMALIZE);
    glEnable(GL_LIGHTING);
    
    glutDisplayFunc(mydisplay);
    
    glutMainLoop();
    return 0;
}



void mydisplay(){
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(90, 1.0, 5.0, 1000.0);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    
    gluLookAt(lookFromPosX, lookFromPosY, lookFromPosZ, lookAtPosX, lookAtPosY, lookAtPosZ, 0.0, 1.0, 0.0);
    
    glEnable(GL_LIGHT0);
    
    GLfloat ambientColor[] = {0.5f, 0.5f, 0.5f, 1.0f};
    glLightModelfv(GL_LIGHT_MODEL_AMBIENT, ambientColor);
    
    GLfloat porchLightColor[] = {0.5f, 0.5f, 0.5f, 1.0f};
    GLfloat porchLightPos[] = {53.0f, 7.0f, 50.01f};
    glLightfv(GL_LIGHT0, GL_DIFFUSE, porchLightColor);
    glLightfv(GL_LIGHT0, GL_POSITION, porchLightPos);
    
    
    createHuman();
    
    glEnable(GL_DEPTH_TEST);
    glEnable(GL_CULL_FACE);
    glFlush();
}

void createHuman(){
    GLfloat shirtmat[] = {1.0, 0.51, 0.88, 1.0};
    glMaterialfv(GL_FRONT, GL_AMBIENT, shirtmat);
    glMaterialfv(GL_FRONT, GL_DIFFUSE, shirtmat);
    glTranslatef(humanPosX, 3.75, humanPosZ);
    
    
    //front torso
    glBegin(GL_POLYGON);
    glVertex3f(0.0, 0.0, 0.0);
    glVertex3f(0.0, 2.5, 0.0);
    glVertex3f(-2.0, 2.5, 0.0);
    glVertex3f(-2.0, 0.0, 0.0);
    glEnd();
    
    //left side
    glBegin(GL_POLYGON);
    glVertex3f(0.0, 0.0, 0.0);
    glVertex3f(0.0, 0.0, -0.5);
    glVertex3f(0.0, 2.5, -0.5);
    glVertex3f(0.0, 2.5, 0.0);
    glEnd();
    
    //right side
    glBegin(GL_POLYGON);
    glVertex3f(-2.0, 0.0, 0.0);
    glVertex3f(-2.0, 2.5, 0.0);
    glVertex3f(-2.0, 2.5, -0.5);
    glVertex3f(-2.0, 0.0, -0.5);
    glEnd();
    
    //front shoulders
    glBegin(GL_POLYGON);
    glVertex3f(-2.75, 2.5, 0.0);
    glVertex3f(0.75, 2.5, 0.0);
    glVertex3f(0.75, 3.0, 0.0);
    glVertex3f(-2.75, 3.0, 0.0);
    glEnd();
    
    //front right arm
    glBegin(GL_POLYGON);
    glVertex3f(-2.75, 0.25, 0.0);
    glVertex3f(-2.25, 0.25, 0.0);
    glVertex3f(-2.25, 2.5, 0.0);
    glVertex3f(-2.75, 2.5, 0.0);
    glEnd();
    
    //front left arm
    glBegin(GL_POLYGON);
    glVertex3f(0.25, 0.25, 0.0);
    glVertex3f(0.75, 0.25, 0.0);
    glVertex3f(0.75, 2.5, 0.0);
    glVertex3f(0.25, 2.5, 0.0);
    glEnd();
    
    //top shoulders
    glBegin(GL_POLYGON);
    glVertex3f(0.75, 3.0, 0.0);
    glVertex3f(0.75, 3.0, -0.5);
    glVertex3f(-2.75, 3.0, -0.5);
    glVertex3f(-2.75, 3.0, 0.0);
    glEnd();
    
    //outside left arm
    glBegin(GL_POLYGON);
    glVertex3f(0.75, 0.25, -0.5);
    glVertex3f(0.75, 3.0, -0.5);
    glVertex3f(0.75, 3.0, 0.0);
    glVertex3f(0.75, 0.25, 0.0);
    glEnd();
    
    //outside right arm
    glBegin(GL_POLYGON);
    glVertex3f(-2.75, 0.25, 0.0);
    glVertex3f(-2.75, 3.0, 0.0);
    glVertex3f(-2.75, 3.0, -0.5);
    glVertex3f(-2.75, 0.25, -0.5);
    glEnd();
    
    //inside left arm
    glBegin(GL_POLYGON);
    glVertex3f(0.25, 0.25, 0.0);
    glVertex3f(0.25, 2.5, 0.0);
    glVertex3f(0.25, 2.5, -0.5);
    glVertex3f(0.25, 0.25, -0.5);
    glEnd();
    
    //inside right arm
    glBegin(GL_POLYGON);
    glVertex3f(-2.25, 0.25, -0.5);
    glVertex3f(-2.25, 2.5, -0.5);
    glVertex3f(-2.25, 2.5, 0.0);
    glVertex3f(-2.25, 0.25, 0.0);
    glEnd();
    
    //left armpit
    glBegin(GL_POLYGON);
    glVertex3f(0.0, 2.5, 0.0);
    glVertex3f(0.0, 2.5, -0.5);
    glVertex3f(0.25, 2.5, -0.5);
    glVertex3f(0.25, 2.5, 0.0);
    glEnd();
    
    //right armpit
    glBegin(GL_POLYGON);
    glVertex3f(-2.0, 2.5, 0.0);
    glVertex3f(-2.25, 2.5, 0.0);
    glVertex3f(-2.25, 2.5, -0.5);
    glVertex3f(-2.0, 2.5, -0.5);
    glEnd();
    
    //back right arm
    glBegin(GL_POLYGON);
    glVertex3f(-2.75, 0.25, -0.5);
    glVertex3f(-2.75, 2.5, -0.5);
    glVertex3f(-2.25, 2.5, -0.5);
    glVertex3f(-2.25, 0.25, -0.5);
    glEnd();
    
    //back left arm
    glBegin(GL_POLYGON);
    glVertex3f(0.25, 0.25, -0.5);
    glVertex3f(0.25, 2.5, -0.5);
    glVertex3f(0.75, 2.5, -0.5);
    glVertex3f(0.75, 0.25, -0.5);
    glEnd();
    
    //back shoulders
    glBegin(GL_POLYGON);
    glVertex3f(-2.75, 2.5, -0.5);
    glVertex3f(-2.75, 3.0, -0.5);
    glVertex3f(0.75, 3.0, -0.5);
    glVertex3f(0.75, 2.5, -0.5);
    glEnd();
    
    //back
    glBegin(GL_POLYGON);
    glVertex3f(0.0, 0.0, -0.5);
    glVertex3f(-2.0, 0.0, -0.5);
    glVertex3f(-2.0, 2.5, -0.5);
    glVertex3f(0.0, 2.5, -0.5);
    glEnd();
    
    
    //front shoulders
    
    glBegin(GL_POLYGON);
    glVertex3f(-2.75, 2.5, 0.0);
    glVertex3f(0.75, 2.5, 0.0);
    glVertex3f(0.75, 3.0, 0.0);
    glVertex3f(-2.75, 3.0, 0.0);
    glEnd();

    
    //front shoulder injury
    if (strncmp(injury, "shoulder", 8) == 0) {
        GLfloat shouldermat[] = {1.0, 0.0, 0.0, 1.0};
        glMaterialfv(GL_FRONT, GL_AMBIENT, shouldermat);
        glMaterialfv(GL_FRONT, GL_DIFFUSE, shouldermat);
        glBegin(GL_POLYGON);
        glVertex3f(-1.0, 2.5, 0.01);
        glVertex3f(0.75, 2.5, 0.01);
        glVertex3f(0.75, 3.0, 0.01);
        glVertex3f(-1.0, 3.0, 0.01);
        glEnd();

    }
    
    if (strncmp(injury, "back", 4) == 0) {
        glPushMatrix();
        
        GLfloat backmat[] = {1.0, 0.0, 0.0, 1.0};
        glMaterialfv(GL_FRONT, GL_AMBIENT, backmat);
        glMaterialfv(GL_FRONT, GL_DIFFUSE, backmat);
        
        glBegin(GL_POLYGON);
        glVertex3f(0.0, 0.0, -0.5);
        glVertex3f(-2.0, 0.0, -0.5);
        glVertex3f(-2.0, 2.5, -0.5);
        glVertex3f(0.0, 2.5, -0.5);
        glEnd();
        glPopMatrix();
        
    }

    
    
    //neck
    GLfloat skinmat[] = {0.85, 0.57, 0.44, 1.0};
    glMaterialfv(GL_FRONT, GL_AMBIENT, skinmat);
    glMaterialfv(GL_FRONT, GL_DIFFUSE, skinmat);
    
    //front neck
    glBegin(GL_POLYGON);
    glVertex3f(-0.75, 3.0, -0.1);
    glVertex3f(-0.75, 3.5, -0.1);
    glVertex3f(-1.25, 3.5, -0.1);
    glVertex3f(-1.25, 3.0, -0.1);
    glEnd();
    
    //left side neck
    glBegin(GL_POLYGON);
    glVertex3f(-0.75, 3.0, -0.4);
    glVertex3f(-0.75, 3.5, -0.4);
    glVertex3f(-0.75, 3.5, -0.1);
    glVertex3f(-0.75, 3.0, -0.1);
    glEnd();
    
    //right side neck
    glBegin(GL_POLYGON);
    glVertex3f(-1.25, 3.0, -0.1);
    glVertex3f(-1.25, 3.5, -0.1);
    glVertex3f(-1.25, 3.5, -0.4);
    glVertex3f(-1.25, 3.0, -0.4);
    glEnd();
    
    //back side neck
    glBegin(GL_POLYGON);
    glVertex3f(-1.25, 3.0, -0.4);
    glVertex3f(-1.25, 3.5, -0.4);
    glVertex3f(-0.75, 3.5, -0.4);
    glVertex3f(-0.75, 3.0, -0.4);
    glEnd();
    
    //left hand
    glPushMatrix();
    glTranslatef(0.5, 0.1, -0.25);
    glutSolidSphere(0.25, 100, 100);
    glPopMatrix();
    
    //right hand
    glPushMatrix();
    glTranslatef(-2.5, 0.1, -0.25);
    glutSolidSphere(0.25, 100, 100);
    glPopMatrix();
    
    //head
    if (strncmp(injury, "concussion", 10) == 0) {
        GLfloat headmat[] = {1.0, 0.0, 0.0, 1.0};
        glMaterialfv(GL_FRONT, GL_AMBIENT, headmat);
        glMaterialfv(GL_FRONT, GL_DIFFUSE, headmat);
        
        
    }
    glPushMatrix();
    glTranslatef(-1.0, 3.9, -0.25);
    glutSolidSphere(0.75, 100, 100);
    glPopMatrix();

    
    
    GLfloat pantsmat[] = {0.18, 0.18, 0.34, 1.0};
    glMaterialfv(GL_FRONT, GL_AMBIENT, pantsmat);
    glMaterialfv(GL_FRONT, GL_DIFFUSE, pantsmat);
    
    //front lower body
    glBegin(GL_POLYGON);
    glVertex3f(0.0, 0.0, 0.0);
    glVertex3f(-2.0, 0.0, 0.0);
    glVertex3f(-2.0, -0.75, 0.0);
    glVertex3f(0.0, -0.75, 0.0);
    glEnd();
    
    //right lower body
    glBegin(GL_POLYGON);
    glVertex3f(0.0, 0.0, 0.0);
    glVertex3f(0.0, -0.75, 0.0);
    glVertex3f(0.0, -0.75, -0.5);
    glVertex3f(0.0, 0.0, -0.5);
    glEnd();
    
    //left lower body
    glBegin(GL_POLYGON);
    glVertex3f(-2.0, 0.0, 0.0);
    glVertex3f(-2.0, 0.0, -0.5);
    glVertex3f(-2.0, -0.75, -0.5);
    glVertex3f(-2.0, -0.75, 0.0);
    glEnd();
    
    //butt
    glBegin(GL_POLYGON);
    glVertex3f(0.0, 0.0, -0.5);
    glVertex3f(0.0, -0.75, -0.5);
    glVertex3f(-2.0, -0.75, -0.5);
    glVertex3f(-2.0, 0.0, -0.5);
    glEnd();
    
    createLeftLeg();
    createRightLeg();
    
    
    
}

void createLeftLeg(){
    glPushMatrix();
    glTranslatef(-1.0, -0.50, 0.0);
    
    
    GLfloat pantsmat1[] = {0.18, 0.18, 0.34, 1.0};
    glMaterialfv(GL_FRONT, GL_AMBIENT, pantsmat1);
    glMaterialfv(GL_FRONT, GL_DIFFUSE, pantsmat1);
    
    //front leg
    glBegin(GL_POLYGON);
    glVertex3f(0.0, 0.0, 0.0);
    glVertex3f(-1.0, 0.0, 0.0);
    glVertex3f(-1.0, -3.0, 0.0);
    glVertex3f(0.0, -3.0, 0.0);
    glEnd();
    
    //left side leg
    glBegin(GL_POLYGON);
    glVertex3f(0.0, 0.0, 0.0);
    glVertex3f(0.0, -3.0, 0.0);
    glVertex3f(0.0, -3.0, -0.5);
    glVertex3f(0.0, 0.0, -0.5);
    glEnd();
    
    //right side leg
    glBegin(GL_POLYGON);
    glVertex3f(-1.0, 0.0, -0.5);
    glVertex3f(-1.0, -3.0, -0.5);
    glVertex3f(-1.0, -3.0, 0.0);
    glVertex3f(-1.0, 0.0, 0.0);
    glEnd();
    
    //back side leg
    glBegin(GL_POLYGON);
    glVertex3f(0.0, 0.0, -0.5);
    glVertex3f(0.0, -3.0, -0.5);
    glVertex3f(-1.0, -3.0, -0.5);
    glVertex3f(-1.0, 0.0, -0.5);
    glEnd();
    
    
    
    
    
    GLfloat shoemat[] = {0.196, 0.6, 0.8, 1.0};
    glMaterialfv(GL_FRONT, GL_AMBIENT, shoemat);
    glMaterialfv(GL_FRONT, GL_DIFFUSE, shoemat);
    
    if (strncmp(injury, "ankle", 8) == 0) {
        GLfloat footmat[] = {1.0, 0.0, 0.0, 1.0};
        glMaterialfv(GL_FRONT, GL_AMBIENT, footmat);
        glMaterialfv(GL_FRONT, GL_DIFFUSE, footmat);
    }

    //shoe front
    glBegin(GL_POLYGON);
    glVertex3f(-0.2, -3.0, 1.0);
    glVertex3f(-0.8, -3.0, 1.0);
    glVertex3f(-0.8, -3.25, 1.0);
    glVertex3f(-0.2, -3.25, 1.0);
    glEnd();
    
    //shoe top
    glBegin(GL_POLYGON);
    glVertex3f(-0.2, -3.0, 1.0);
    glVertex3f(-0.2, -3.0, -0.5);
    glVertex3f(-0.8, -3.0, -0.5);
    glVertex3f(-0.8, -3.0, 1.0);
    glEnd();
    
    //shoe left
    glBegin(GL_POLYGON);
    glVertex3f(-0.2, -3.0, 1.0);
    glVertex3f(-0.2, -3.25, 1.0);
    glVertex3f(-0.2, -3.25, -0.5);
    glVertex3f(-0.2, -3.0, -0.5);
    glEnd();
    
    //shoe right
    glBegin(GL_POLYGON);
    glVertex3f(-0.8, -3.0, 1.0);
    glVertex3f(-0.8, -3.0, -0.5);
    glVertex3f(-0.8, -3.25, -0.5);
    glVertex3f(-0.8, -3.25, 1.0);
    glEnd();
    
    //shoe bottom
    glBegin(GL_POLYGON);
    glVertex3f(-0.2, -3.25, 1.0);
    glVertex3f(-0.8, -3.25, 1.0);
    glVertex3f(-0.8, -3.25, -0.5);
    glVertex3f(-0.2, -3.25, -0.5);
    glEnd();
    
    //shoe back
    glBegin(GL_POLYGON);
    glVertex3f(-0.2, -3.0, -0.5);
    glVertex3f(-0.2, -3.25, -0.5);
    glVertex3f(-0.8, -3.25, -0.5);
    glVertex3f(-0.8, -3.0, -0.5);
    glEnd();
    
    glPopMatrix();
}

void createRightLeg(){
    glPushMatrix();
    glTranslatef(0.0, -0.50, 0.0);
    
    
    GLfloat pantsmat1[] = {0.18, 0.18, 0.34, 1.0};
    glMaterialfv(GL_FRONT, GL_AMBIENT, pantsmat1);
    glMaterialfv(GL_FRONT, GL_DIFFUSE, pantsmat1);
    
    //front leg
    glBegin(GL_POLYGON);
    glVertex3f(0.0, 0.0, 0.0);
    glVertex3f(-1.0, 0.0, 0.0);
    glVertex3f(-1.0, -3.0, 0.0);
    glVertex3f(0.0, -3.0, 0.0);
    glEnd();
    
    //left side leg
    glBegin(GL_POLYGON);
    glVertex3f(0.0, 0.0, 0.0);
    glVertex3f(0.0, -3.0, 0.0);
    glVertex3f(0.0, -3.0, -0.5);
    glVertex3f(0.0, 0.0, -0.5);
    glEnd();
    
    //right side leg
    glBegin(GL_POLYGON);
    glVertex3f(-1.0, 0.0, -0.5);
    glVertex3f(-1.0, -3.0, -0.5);
    glVertex3f(-1.0, -3.0, 0.0);
    glVertex3f(-1.0, 0.0, 0.0);
    glEnd();
    
    //back side leg
    glBegin(GL_POLYGON);
    glVertex3f(0.0, 0.0, -0.5);
    glVertex3f(0.0, -3.0, -0.5);
    glVertex3f(-1.0, -3.0, -0.5);
    glVertex3f(-1.0, 0.0, -0.5);
    glEnd();
    
    //knee
    if (strncmp(injury, "knee", 8) == 0) {
        glPushMatrix();
        
        GLfloat kneemat[] = {1.0, 0.0, 0.0, 1.0};
        glMaterialfv(GL_FRONT, GL_AMBIENT, kneemat);
        glMaterialfv(GL_FRONT, GL_DIFFUSE, kneemat);
        
        glBegin(GL_POLYGON);
        glVertex3f(0.0, -1.0, 0.0);
        glVertex3f(-1.0, -1.0, 0.0);
        glVertex3f(-1.0, -2.0, 0.0);
        glVertex3f(0.0, -2.0, 0.0);
        glEnd();
        glPopMatrix();
        
    }

    if (strncmp(injury, "hamstring", 8) == 0) {
        lookFromPosZ = 70;
        glPushMatrix();
        
        GLfloat hammat[] = {1.0, 0.0, 0.0, 1.0};
        glMaterialfv(GL_FRONT, GL_AMBIENT, hammat);
        glMaterialfv(GL_FRONT, GL_DIFFUSE, hammat);
        
        glBegin(GL_POLYGON);
        glVertex3f(0.0, -1.0, -0.5);
        glVertex3f(-1.0, -1.0, -0.5);
        glVertex3f(-1.0, -2.0, -0.5);
        glVertex3f(0.0, -2.0, -0.5);
        glEnd();
        glPopMatrix();
        
    }

    

    
    
    GLfloat shoemat[] = {0.196, 0.6, 0.8, 1.0};
    glMaterialfv(GL_FRONT, GL_AMBIENT, shoemat);
    glMaterialfv(GL_FRONT, GL_DIFFUSE, shoemat);
    
    //shoe front
    glBegin(GL_POLYGON);
    glVertex3f(-0.2, -3.0, 1.0);
    glVertex3f(-0.8, -3.0, 1.0);
    glVertex3f(-0.8, -3.25, 1.0);
    glVertex3f(-0.2, -3.25, 1.0);
    glEnd();
    
    //shoe top
    glBegin(GL_POLYGON);
    glVertex3f(-0.2, -3.0, 1.0);
    glVertex3f(-0.2, -3.0, -0.5);
    glVertex3f(-0.8, -3.0, -0.5);
    glVertex3f(-0.8, -3.0, 1.0);
    glEnd();
    
    //shoe left
    glBegin(GL_POLYGON);
    glVertex3f(-0.2, -3.0, 1.0);
    glVertex3f(-0.2, -3.25, 1.0);
    glVertex3f(-0.2, -3.25, -0.5);
    glVertex3f(-0.2, -3.0, -0.5);
    glEnd();
    
    //shoe right
    glBegin(GL_POLYGON);
    glVertex3f(-0.8, -3.0, 1.0);
    glVertex3f(-0.8, -3.0, -0.5);
    glVertex3f(-0.8, -3.25, -0.5);
    glVertex3f(-0.8, -3.25, 1.0);
    glEnd();
    
    //shoe bottom
    glBegin(GL_POLYGON);
    glVertex3f(-0.2, -3.25, 1.0);
    glVertex3f(-0.8, -3.25, 1.0);
    glVertex3f(-0.8, -3.25, -0.5);
    glVertex3f(-0.2, -3.25, -0.5);
    glEnd();
    
    //shoe back
    glBegin(GL_POLYGON);
    glVertex3f(-0.2, -3.0, -0.5);
    glVertex3f(-0.2, -3.25, -0.5);
    glVertex3f(-0.8, -3.25, -0.5);
    glVertex3f(-0.8, -3.0, -0.5);
    glEnd();
    
    glPopMatrix();
}










