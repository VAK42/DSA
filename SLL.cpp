#include <iostream>
using namespace std;

struct Node {
    int data;
    Node* next;
};

Node* createNode(int data) {
    Node* newNode = new Node();
    newNode->data = data;
    newNode->next = nullptr;
    return newNode;
}

void createList(Node*& head) {
    head = nullptr;
    int n, value;

    cout << "Nhap So Luong Node: ";
    cin >> n;

    if (n <= 0) {
        cout << "So Luong Node Phai Lon Hon 0!" << endl;
        return;
    }

    for (int i = 0; i < n; ++i) {
        cout << "Nhap Gia Tri Cua Node " << i + 1 << ": ";
        cin >> value;
        Node* newNode = createNode(value);

        if (head == nullptr) {
            head = newNode;
        } else {
            Node* temp = head;
            while (temp->next != nullptr) {
                temp = temp->next;
            }
            temp->next = newNode;
        }
    }
}

void prtList(Node* head) {
    Node* temp = head;
    while (temp != nullptr) {
        cout << temp->data << " ";
        temp = temp->next;
    }
    cout << endl;
}

void insHead(Node*& head, int data) {
    Node* newNode = createNode(data);
    newNode->next = head;
    head = newNode;
}

void insTail(Node*& head, int data) {
    Node* newNode = createNode(data);
    if (head == nullptr) {
        head = newNode;
    } else {
        Node* temp = head;
        while (temp->next != nullptr) {
            temp = temp->next;
        }
        temp->next = newNode;
    }
}

void insPos(Node*& head, int data, int pos) {
    if (pos == 0) {
        insHead(head, data);
        return;
    }

    Node* newNode = createNode(data);
    Node* temp = head;
    int currentIndex = 0;

    while (temp != nullptr && currentIndex < pos - 1) {
        temp = temp->next;
        ++currentIndex;
    }

    if (temp == nullptr) {
        cout << "Vi Tri Ko Hop Le!" << endl;
        delete newNode;
        return;
    }

    newNode->next = temp->next;
    temp->next = newNode;
}

void delNode(Node*& head, int key) {
    if (head == nullptr) {
        cout << "Danh Sach Rong!" << endl;
        return;
    }

    Node* temp = head;
    Node* prev = nullptr;

    if (temp != nullptr && temp->data == key) {
        head = temp->next;
        delete temp;
        return;
    }

    while (temp != nullptr && temp->data != key) {
        prev = temp;
        temp = temp->next;
    }

    if (temp == nullptr) {
        cout << "Node Co Gia Tri " << key << " Ko Ton Tai!" << endl;
        return;
    }

    prev->next = temp->next;
    delete temp;
}

int main() {
    Node* head = nullptr;
    int choice, data, pos, key;
    
    cout << "\n============Menu============\n";
    cout << "1. Tao Danh Sach Lien Ket Don\n";
    cout << "2. In Danh Sach\n";
    cout << "3. Chen Node Tai Vi Tri Dau Tien\n";
    cout << "4. Chen Node Tai Vi Tri Cuoi Cung\n";
    cout << "5. Chen Node Tai Vi Tri Khac\n";
    cout << "6. Xoa Node\n";
    cout << "7. Thoat Chuong Trinh\n";

    while (true) {
        cout << "Hay Nhap Lua Chon Cua Ban: ";
        cin >> choice;

        switch (choice) {
            case 1:
                createList(head);
                cout << "Tao DSLK Thanh Cong!\n";
                break;
            case 2:
                if (head == nullptr) {
                    cout << "DSLK Ko Co Phan Tu\n";
                } else {
                    prtList(head);
                }
                break;
            case 3:
                cout << "Nhap Gia Tri De Chen Vao Vi Tri Dau Tien: ";
                cin >> data;
                insHead(head, data);
                cout << "Node Da Dc Chen!\n";
                break;
            case 4:
                cout << "Nhap Gia Tri De Chen Vao Vi Tri Cuoi Cung: ";
                cin >> data;
                insTail(head, data);
                cout << "Node Da Dc Chen!\n";
                break;
            case 5:
                cout << "Nhap Gia Tri De Chen: ";
                cin >> data;
                cout << "Nhap Vi Tri De Chen: ";
                cin >> pos;
                insPos(head, data, pos);
                cout << "Node Da Dc Chen Tai Vi Tri " << pos << "!\n";
                break;
            case 6:
                cout << "Nhap Gia Tri De Xoa: ";
                cin >> key;
                delNode(head, key);
                cout << "Node Co Gia Tri " << key << " Da Dc Xoa!\n";
                break;
            case 7:
                cout << "Thoat Chuong Trinh!\n";
                return 0;
            default:
                cout << "Lua Chon Ko Hop Le! Vui Long Chon Lai!\n";
        }
    }
    
    return 0;
}
