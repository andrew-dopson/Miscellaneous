
Name [A-Z][a-z]+[ ][A-Z][a-z]+
Word [a-z]+
Wordd [A-Za-z\(\)\.,']+
Space [ \t]


%{
    #include <stdio.h>
    #include <string.h>
    #include <GLUT/GLUT.h>
    
    void mydisplay();
    void createHuman();
    void createRightLeg();
    void createLeftLeg();
    
    const char* teams[32] = {"Buffalo Bills", "Miami Dolphins", "New England Patriots", "New York Jets", "Baltimore Ravens", "Cincinnati Bengals", "Cleveland Browns", "Pittsburgh Steelers", "Houston Texans", "Indianapolis Colts", "Jacksonville Jaguars", "Tennessee Titans", "Denver Broncos", "Kansas City Chiefs", "Oakland Raiders", "San Diego Chargers", "Dallas Cowboys", "New York Giants", "Philadelphia Eagles", "Washington Redskins", "Chicago Bears", "Detroit Lions", "Green Bay Packers", "Minnesota Vikings", "Atlanta Falcons", "Carolina Panthers", "New Orleans Saints", "Tampa Bay Buccaneers", "Arizona Cardinals", "St. Louis Rams", "San Francisco 49ers", "Seattle Seahawks"};

    const char* bodyPart[6] = {"back", "ankle", "hamstring", "concussion", "shoulder", "knee"};

    const char* name;
    int compareLimit = 20;
    const char* injury;
    
    int team = 0;
    
    void mydisplay(){
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        gluPerspective(90, 1.0, 5.0, 1000.0);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
        gluLookAt(40.0, 7.0, 90.0, 40.0, 4.0, 80.0, 0.0, 1.0, 0.0);
        
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
        GLfloat shirtmat[] = {1.0, 0.11, 0.68, 1.0};
        glMaterialfv(GL_FRONT, GL_AMBIENT, shirtmat);
        glMaterialfv(GL_FRONT, GL_DIFFUSE, shirtmat);
        glTranslatef(40.0, 3.75, 80.0);
        
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
        glPushMatrix();
        if(strncmp(injury, "shoulder", 8) == 0){
            GLfloat backmat[] = {1.0, 0.0, 0.0, 1.0};
            glMaterialfv(GL_FRONT, GL_AMBIENT, backmat);
            glMaterialfv(GL_FRONT, GL_DIFFUSE, backmat);
            
        }
        
        glBegin(GL_POLYGON);
        glVertex3f(-2.75, 2.5, 0.0);
        glVertex3f(0.75, 2.5, 0.0);
        glVertex3f(0.75, 3.0, 0.0);
        glVertex3f(-2.75, 3.0, 0.0);
        glEnd();
        glPopMatrix();
        
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
        glPushMatrix();
        if(strncmp(injury, "back", 4) == 0){
            GLfloat backmat[] = {1.0, 0.0, 0.0, 1.0};
            glMaterialfv(GL_FRONT, GL_AMBIENT, backmat);
            glMaterialfv(GL_FRONT, GL_DIFFUSE, backmat);
            
        }
        
        glBegin(GL_POLYGON);
        glVertex3f(0.0, 0.0, -0.5);
        glVertex3f(-2.0, 0.0, -0.5);
        glVertex3f(-2.0, 2.5, -0.5);
        glVertex3f(0.0, 2.5, -0.5);
        glEnd();
        glPopMatrix();
        
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
        glPushMatrix();
        if(strncmp(injury, "concussion", 10) == 0){
            GLfloat backmat[] = {1.0, 0.43, 0.78, 1.0};
            glMaterialfv(GL_FRONT, GL_AMBIENT, backmat);
            glMaterialfv(GL_FRONT, GL_DIFFUSE, backmat);
            
        }
        glPushMatrix();
        
        glTranslatef(-1.0, 3.9, -0.25);
        glutSolidSphere(0.75, 100, 100);
        glPopMatrix();
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
        
        //knee
        glPushMatrix();
        if(strncmp(injury, "knee", 4) == 0){
            GLfloat backmat[] = {1.0, 0.0, 0.0, 1.0};
            glMaterialfv(GL_FRONT, GL_AMBIENT, backmat);
            glMaterialfv(GL_FRONT, GL_DIFFUSE, backmat);
            
            glBegin(GL_POLYGON);
            glVertex3f(0.0, -1.5, 0.0);
            glVertex3f(-1.0, -1.5, 0.0);
            glVertex3f(-1.0, -2.0, 0.0);
            glVertex3f(0.0, -2.0, 0.0);
            glEnd();
            glPopMatrix();
        }
        
        //right side leg
        glBegin(GL_POLYGON);
        glVertex3f(-1.0, 0.0, -0.5);
        glVertex3f(-1.0, -3.0, -0.5);
        glVertex3f(-1.0, -3.0, 0.0);
        glVertex3f(-1.0, 0.0, 0.0);
        glEnd();
        
        //back side leg
        glPushMatrix();
        if(strncmp(injury, "hamstring", 9) == 0){
            GLfloat backmat[] = {1.0, 0.0, 0.0, 1.0};
            glMaterialfv(GL_FRONT, GL_AMBIENT, backmat);
            glMaterialfv(GL_FRONT, GL_DIFFUSE, backmat);
            
        }
        glBegin(GL_POLYGON);
        glVertex3f(0.0, 0.0, -0.5);
        glVertex3f(0.0, -3.0, -0.5);
        glVertex3f(-1.0, -3.0, -0.5);
        glVertex3f(-1.0, 0.0, -0.5);
        glEnd();
        glPopMatrix();
        
        
        GLfloat shoemat[] = {0.196, 0.6, 0.8, 1.0};
        glMaterialfv(GL_FRONT, GL_AMBIENT, shoemat);
        glMaterialfv(GL_FRONT, GL_DIFFUSE, shoemat);
        
        glPushMatrix();
        if(strncmp(injury, "ankle", 5) == 0){
            GLfloat backmat[] = {1.0, 0.0, 0.0, 1.0};
            glMaterialfv(GL_FRONT, GL_AMBIENT, backmat);
            glMaterialfv(GL_FRONT, GL_DIFFUSE, backmat);
            
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

    
%}

%%

{Name}              {for(int i = 0; i < 32; i++){if(strncmp(teams[i],yytext, compareLimit) == 0){printf("Team: %s\n", teams[i]); team = 1;}}; if(team == 0){printf("Player: %s\n", yytext);}; team = 0;}

{Word}              {for(int i = 0; i < 32; i++){if(strncmp(bodyPart[i], yytext, compareLimit) == 0){printf("Suffering from a %s injury.\n", bodyPart[i]); injury = yytext;}};}
{Wordd}             {}
{Space}             {}

%%

int main(int argc, char** argv){
    yyin = fopen("test.txt", "r");
    yylex();
    
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


