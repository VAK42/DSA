#include <iostream>
using namespace std;

struct DNode {
    int data;
    DNode* next;
    DNode* prev;
};

DNode* createDNode(int data) {
    DNode* newNode = new DNode();
    newNode->data = data;
    newNode->next = nullptr;
    newNode->prev = nullptr;
    return newNode;
}

void createDList(DNode*& head, DNode*& tail) {
    head = nullptr;
    tail = nullptr;
    int n, value;

    cout << "Nhap So Luong Node: ";
    cin >> n;

    for (int i = 0; i < n; ++i) {
        cout << "Nhap Gia Tri Cua Node " << i + 1 << ": ";
        cin >> value;
        DNode* newNode = createDNode(value);

        if (head == nullptr) {
            head = newNode;
            tail = newNode;
        } else {
            tail->next = newNode;
            newNode->prev = tail;
            tail = newNode;
        }
    }
}

void prtDListFwd(DNode* head) {
    DNode* temp = head;
    while (temp != nullptr) {
        cout << temp->data << " ";
        temp = temp->next;
    }
    cout << endl;
}

void prtDListBwd(DNode* tail) {
    DNode* temp = tail;
    while (temp != nullptr) {
        cout << temp->data << " ";
        temp = temp->prev;
    }
    cout << endl;
}

void insHead(DNode*& head, DNode*& tail, int value) {
    DNode* newNode = createDNode(value);
    if (head == nullptr) {
        head = newNode;
        tail = newNode;
    } else {
        newNode->next = head;
        head->prev = newNode;
        head = newNode;
    }
}

void insTail(DNode*& head, DNode*& tail, int value) {
    DNode* newNode = createDNode(value);
    if (head == nullptr) {
        head = newNode;
        tail = newNode;
    } else {
        tail->next = newNode;
        newNode->prev = tail;
        tail = newNode;
    }
}

void insPos(DNode*& head, DNode*& tail, int value, int index) {
    if (index < 0) {
        cout << "Index Ko Hop Le!\n\n";
        return;
    }

    DNode* newNode = createDNode(value);

    if (index == 0) {
        insHead(head, tail, value);
        return;
    }

    DNode* temp = head;
    int currentIndex = 0;

    while (temp != nullptr && currentIndex < index - 1) {
        temp = temp->next;
        ++currentIndex;
    }

    if (temp == nullptr) {
        cout << "Index Ko Hop Le!\n\n";
        delete newNode;
        return;
    }

    newNode->next = temp->next;
    newNode->prev = temp;
    if (temp->next != nullptr) {
        temp->next->prev = newNode;
    }
    temp->next = newNode;
    if (newNode->next == nullptr) {
        tail = newNode;
    }
}

void delNode(DNode*& head, DNode*& tail, int value) {
    DNode* temp = head;
    
    while (temp != nullptr) {
        if (temp->data == value) {
            if (temp == head) {
                head = temp->next;
                if (head != nullptr) {
                    head->prev = nullptr;
                } else {
                    tail = nullptr;
                }
            } else {
                if (temp->prev != nullptr) {
                    temp->prev->next = temp->next;
                }
                if (temp->next != nullptr) {
                    temp->next->prev = temp->prev;
                }
                if (temp == tail) {
                    tail = temp->prev;
                }
            }
            delete temp;
            cout << "Node Co Gia Tri " << value << " Da Dc Xoa!\n\n";
            return;
        }
        temp = temp->next;
    }
    
    cout << "Node Co Gia Tri " << value << " Ko Ton Tai!\n\n";
}

int main() {
    DNode* head = nullptr;
    DNode* tail = nullptr;
    int choice, value, index;

    cout << "===============MENU===============\n";
    cout << "1. Tao Danh Sach Lien Ket Doi\n";
    cout << "2. In DSLK Xuoi\n";
    cout << "3. In DSLK Nguoc\n";
    cout << "4. Chen Node Vao Vi Tri Dau Tien\n";
    cout << "5. Chen Node Vao Vi Tri Cuoi Cung\n";
    cout << "6. Chen Node Vao Vi Tri Khac\n";
    cout << "7. Xoa Node\n";
    cout << "8. Thoat Chuong Trinh\n";
    
    while (true) {
        cout << "Lua Chon Cua Ban: ";
        cin >> choice;

        switch (choice) {
            case 1:
                createDList(head, tail);
                cout << "Da Tao DSLK Doi Thanh Cong!\n\n";
                break;
            case 2:
                prtDListFwd(head);
                break;
            case 3:
                if (tail != nullptr) {
                    prtDListBwd(tail);
                } else {
                    cout << "DSLK Ko Co Node!\n\n";
                }
                break;
            case 4:
                cout << "Nhap Gia Tri De Chen Vao Vi Tri Dau Tien: ";
                cin >> value;
                insHead(head, tail, value);
                cout << "Da Chen Thanh Cong!\n\n";
                break;
            case 5:
                cout << "Nhap Gia Tri De Chen Vao Vi Tri Cuoi Cung: ";
                cin >> value;
                insTail(head, tail, value);
                cout << "Da Chen Thanh Cong!\n\n";
                break;
            case 6:
                cout << "Nhap Gia Tri De Chen Vao: ";
                cin >> value;
                cout << "Nhap Vi Tri De Chen Vao: ";
                cin >> index;
                insPos(head, tail, value, index);
                break;
            case 7:
                cout << "Nhap Gia Tri Muon Xoa: ";
                cin >> value;
                delNode(head, tail, value);
                break;
            case 8:
                cout << "Thoat Chuong Trinh!\n\n";
                return 0;
            default:
                cout << "Lua Chon Ko Hop Le! Vui Long Chon Lai!\n\n";
                break;
        }
    }
    
    return 0;
}
