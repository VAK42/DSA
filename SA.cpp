#include <iostream>
using namespace std;

int top = -1;
int* stack;
int stackSize;

bool isEmpty() {
    return top == -1;
}

bool isFull() {
    return top == stackSize - 1;
}

void push(int item) {
    if (isFull()) {
        cout << "Ngan Xep Day! Ko The Push!" << endl;
        return;
    }
    stack[++top] = item;
}

void pop() {
    if (isEmpty()) {
        cout << "Ngap Xep Rong! Ko The Pop!" << endl;
        return;
    }
    top--;
}

int peek() {
    if (isEmpty()) {
        cout << "Ngap Xep Rong! Ko Co Phan Tu Nao De Peek!" << endl;
        return -1;
    }
    return stack[top];
}

void displayStack() {
    if (isEmpty()) {
        cout << "Ngap Xep Rong!" << endl;
        return;
    }
    cout << "Noi Dung Ngan Xep Tu Duoi Len Tren: " << endl;
    for (int i = 0; i <= top; i++) {
        cout << stack[i] << " ";
    }
    cout << endl;
}

int main() {
    int choice, value;
    
    cout << "Nhap Kich Thuoc Cua Stack: ";
    cin >> stackSize;
    
    if (stackSize <= 0) {
        cout << "Kich Thuoc Khong Hop Le!" << endl;
        return 1;
    }
    
    stack = new int[stackSize];
    
    cout << "==========MENU==========\n";
    cout << "1. Push\n";
    cout << "2. Pop\n";
    cout << "3. Get Top\n";
    cout << "4. Hien Thi Stack\n";
    cout << "5. Thoat Chuong Trinh\n";
    
    do {
        cout << "Lua Chon Cua Ban: ";
        cin >> choice;

        switch (choice) {
            case 1:
                cout << "Nhap Gia Tri De Push: ";
                cin >> value;
                push(value);
                cout << "Push Thanh Cong!\n\n";
                break;
            case 2:
                pop();
                cout << "Pop Thanh Cong!\n\n";
                break;
            case 3:
                cout << "Top Value: " << peek() << endl;
                cout << "\n\n";
                break;
            case 4:
                displayStack();
                cout << "\n\n";
                break;
            case 5:
                cout << "Thoat Chuong Trinh" << endl;
                break;
            default:
                cout << "Lua Chon Ko Hop Le! Vui Long Chon Lai!" << endl;
                cout << "\n\n";
        }
    } while (choice != 5);
    
    delete[] stack;
    return 0;
}
