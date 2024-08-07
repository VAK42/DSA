#include <iostream>
using namespace std;

void push(char stack[], int& top, char c) {
    if (top < 999) {
        stack[++top] = c;
    } else {
        cout << "Stack Overflow!" << endl;
    }
}

char pop(char stack[], int& top) {
    if (top >= 0) {
        return stack[top--];
    } else {
        cout << "Stack Underflow!" << endl;
        return '\0';
    }
}

bool isEmpty(int top) {
    return top == -1;
}

void reverseString(char* input) {
    char stack[1000];
    int top = -1;
    int i = 0;
    
    while (input[i] != '\0') {
        push(stack, top, input[i]);
        i++;
    }
    
    cout << "Chuoi Dao Nguoc: ";
    while (!isEmpty(top)) {
        cout << pop(stack, top);
    }
    cout << endl;
}

bool checkPalindrome(char* input) {
    char stack[1000];
    int top = -1;
    int i = 0;
    
    while (input[i] != '\0') {
        push(stack, top, input[i]);
        i++;
    }
    
    for (int j = 0; j < i; j++) {
        if (input[j] != pop(stack, top)) {
            return false;
        }
    }
    return true;
}

int main() {
    char input[1000];
    cout << "Nhap Chuoi Ki Tu: ";
    cin.getline(input, 1000);
    
    reverseString(input);
    if (checkPalindrome(input)) {
        cout << "Chuoi La Palindrome!" << endl;
    } else {
        cout << "Chuoi Ko Phai La Palindrome!" << endl;
    }
    
    return 0;
}
