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

void createDList(Node*& head) {
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
            head->next = head;
        } else {
            Node* temp = head;
            while (temp->next != head) {
                temp = temp->next;
            }
            temp->next = newNode;
            newNode->next = head;
        }
    }
}

void prtList(Node* head) {
    if (head == nullptr) {
        cout << "Danh Sach Lien Ket Ko Co Phan Tu\n";
        return;
    }
    
    Node* temp = head;
    do {
        cout << temp->data << " ";
        temp = temp->next;
    } while (temp != head);
    cout << endl;
}

void insHead(Node*& head, int data) {
    Node* newNode = createNode(data);
    if (head == nullptr) {
        head = newNode;
        head->next = head;
    } else {
        Node* temp = head;
        while (temp->next != head) {
            temp = temp->next;
        }
        newNode->next = head;
        head = newNode;
        temp->next = head;
    }
}

void insTail(Node*& head, int data) {
    Node* newNode = createNode(data);
    if (head == nullptr) {
        head = newNode;
        head->next = head;
    } else {
        Node* temp = head;
        while (temp->next != head) {
            temp = temp->next;
        }
        temp->next = newNode;
        newNode->next = head;
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
        if (temp == head) break;
    }

    if (temp == nullptr || (temp->next == head && currentIndex < pos - 1)) {
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

    do {
        if (temp->data == key) {
            if (temp == head) {
                if (head->next == head) {
                    delete head;
                    head = nullptr;
                } else {
                    prev = head;
                    while (prev->next != head) {
                        prev = prev->next;
                    }
                    head = head->next;
                    prev->next = head;
                    delete temp;
                }
            } else {
                prev->next = temp->next;
                delete temp;
            }
            return;
        }
        prev = temp;
        temp = temp->next;
    } while (temp != head);

    cout << "Node Co Gia Tri " << key << " Ko Ton Tai!" << endl;
}

int main() {
    Node* head = nullptr;
    int choice, data, pos, key;
    
    cout << "\n============Menu============\n";
    cout << "1. Tao Danh Sach Lien Ket Vong\n";
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
                createDList(head);
                cout << "Tao Danh Sach Lien Ket Thanh Cong!\n\n";
                break;
            case 2:
                prtList(head);
                break;
            case 3:
                cout << "Nhap Gia Tri De Chen Vao Vi Tri Dau Tien: ";
                cin >> data;
                insHead(head, data);
                cout << "Node Da Dc Chen!\n\n";
                break;
            case 4:
                cout << "Nhap Gia Tri De Chen Vao Vi Tri Cuoi Cung: ";
                cin >> data;
                insTail(head, data);
                cout << "Node Da Dc Chen!\n\n";
                break;
            case 5:
                cout << "Nhap Gia Tri De Chen: ";
                cin >> data;
                cout << "Nhap Vi Tri De Chen: ";
                cin >> pos;
                insPos(head, data, pos);
                break;
            case 6:
                cout << "Nhap Gia Tri De Xoa: ";
                cin >> key;
                delNode(head, key);
                break;
            case 7:
                cout << "Thoat Chuong Trinh!\n\n";
                return 0;
            default:
                cout << "Lua Chon Ko Hop Le! Vui Long Chon Lai!\n\n";
        }
    }
    
    return 0;
}
