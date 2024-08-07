#include <iostream>
using namespace std;

int front = 0;
int rear = -1;
int* queue;
int queueSize;
int count = 0;

bool isEmpty() {
    return count == 0;
}

bool isFull() {
    return count == queueSize;
}

void enqueue(int item) {
    if (isFull()) {
        cout << "Hang Doi Day! Ko The Enqueue!" << endl;
        return;
    }
    rear = (rear + 1) % queueSize;
    queue[rear] = item;
    count++;
}

void dequeue() {
    if (isEmpty()) {
        cout << "Hang Doi Rong! Ko The Dequeue!" << endl;
        return;
    }
    front = (front + 1) % queueSize;
    count--;
}

int peek() {
    if (isEmpty()) {
        cout << "Hang Doi Rong! Ko Co Phan Tu Nao De Peek!" << endl;
        return -1;
    }
    return queue[front];
}

void displayQueue() {
    if (isEmpty()) {
        cout << "Hang Doi Rong!" << endl;
        return;
    }
    cout << "Noi Dung Hang Doi Tu Front Den Rear: " << endl;
    for (int i = 0; i < count; i++) {
        cout << queue[(front + i) % queueSize] << " ";
    }
    cout << endl;
}

int main() {
    int choice, value;
    
    cout << "Nhap Kich Thuoc Cua Hang Doi: ";
    cin >> queueSize;
    
    if (queueSize <= 0) {
        cout << "Kich Thuoc Khong Hop Le!" << endl;
        return 1;
    }
    
    queue = new int[queueSize];
    
    cout << "==========MENU==========\n";
    cout << "1. Enqueue\n";
    cout << "2. Dequeue\n";
    cout << "3. Get Front\n";
    cout << "4. Hien Thi Hang Doi\n";
    cout << "5. Thoat Chuong Trinh\n";
    
    do {
        cout << "Lua Chon Cua Ban: ";
        cin >> choice;

        switch (choice) {
            case 1:
                cout << "Nhap Gia Tri De Enqueue: ";
                cin >> value;
                enqueue(value);
                cout << "Enqueue Thanh Cong!\n\n";
                break;
            case 2:
                dequeue();
                cout << "Dequeue Thanh Cong!\n\n";
                break;
            case 3:
                cout << "Front Value: " << peek() << endl;
                cout << "\n\n";
                break;
            case 4:
                displayQueue();
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
    
    delete[] queue;
    return 0;
}
