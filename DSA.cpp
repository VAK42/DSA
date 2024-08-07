#include <iostream>
#include <cmath>
using namespace std;

void defaultArr(int n, int* arr) {
    for (int i = 0; i < n; i++) {
        cout << arr[i] << " ";
    }
    cout << "\n";
}

void reverseArr(int n, int* arr) {
    for (int i = n - 1; i >= 0; i--) {
        cout << arr[i] << " ";
    }
    cout << "\n";
}

void arrEven(int n, int* arr) {
    for (int i = 0; i < n; i++) {
        if (arr[i] % 2 == 0) {
            cout << arr[i] << " ";
        }
    }
    cout << "\n";
}

void arrOdd(int n, int* arr) {
    for (int i = 0; i < n; i++) {
        if (arr[i] % 2 != 0) {
            cout << arr[i] << " ";
        }
    }
    cout << "\n";
}

void isPrime(int n, int* arr) {
    for (int i = 0; i < n; i++) {
        bool prime = true;

        if (arr[i] <= 1) {
            prime = false;
        } else if (arr[i] == 2) {
            cout << arr[i] << " ";
        } else if (arr[i] % 2 == 0) {
            prime = false;
        } else {
            for (int j = 3; j <= sqrt(arr[i]); j++) {
                if (arr[i] % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                cout << arr[i] << " ";
            }
        }
    }
    cout << "\n";
}

void isPerfectSquare(int n, int* arr) {
    for (int i = 0; i < n; i++) {
        if (arr[i] < 0) {
            continue;
        }
        
        int root = static_cast<int>(sqrt(arr[i]));
        if (root * root == arr[i]) {
            cout << arr[i] << " ";
        }
    }
    cout << "\n";
}

void sortArrAsc(int n, int* arr) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (arr[i] < arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
    defaultArr(n, arr);
    cout << "\n";
}

void sortArrDesc(int n, int* arr) {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < i; j++) {
            if (arr[i] > arr[j]) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
    }
    defaultArr(n, arr);
    cout << "\n";
}

void addElement(int& n, int*& arr) {
    int m;
    cout << "So Luong Phan Tu Muon Them Vao: ";
    cin >> m;

    int* newArr = new int[n + m];
    
    for (int i = 0; i < n; i++) {
        newArr[i] = arr[i];
    }
    
    for (int i = 0; i < m; i++) {
        int index;
        cout << "Nhap Vi Tri Can Nhap Cho Phan Tu " << i + 1 << ": ";
        cin >> index;
        if (index < 0 || index > n + i) {
            cout << "Vi Tri Khong Hop Le! Vui Long Nhap Lai: ";
            i--;
            continue;
        }
        cout << "Nhap Gia Tri Can Them Vao: ";
        int value;
        cin >> value;

        for (int j = n + i - 1; j >= index; j--) {
            newArr[j + 1] = newArr[j];
        }
        newArr[index] = value;
    }

    n += m;
    delete[] arr;
    arr = newArr;

    cout << "Phan Tu Da Duoc Them!" << endl;
}

void removeElement(int& n, int*& arr) {
    int index;
    cout << "Nhap Vi Tri Can Xoa: ";
    cin >> index;
    
    if (index < 0 || index >= n) {
        cout << "Vi Tri Khong Hop Le!\n";
        return;
    }

    int* newArr = new int[n - 1];

    for (int i = 0; i < n - 1; i++) {
        if (i < index) {
            newArr[i] = arr[i];
        } else {
            newArr[i] = arr[i + 1];
        }
    }

    delete[] arr;
    arr = newArr;
    n--;

    cout << "Phan Tu Da Duoc Xoa!" << endl;
}

void modifyElement(int n, int* arr) {
    int index, value;
    cout << "Nhap Vi Tri Can Sua: ";
    cin >> index;
    
    if (index < 0 || index >= n) {
        cout << "Vi Tri Khong Hop Le!\n";
        return;
    }

    cout << "Nhap Gia Tri Moi: ";
    cin >> value;

    arr[index] = value;
    
    cout << "Phan Tu Da Duoc Sua!" << endl;
}

void searchElement(int n, int* arr) {
    int value;
    bool found = false;

    cout << "Nhap Gia Tri Can Tim: ";
    cin >> value;

    cout << "Vi Tri Xuat Hien: ";
    for (int i = 0; i < n; i++) {
        if (arr[i] == value) {
            cout << i << " ";
            found = true;
        }
    }

    if (!found) {
        cout << "Khong Tim Thay Gia Tri!\n";
    } else {
        cout << "\n";
    }
}

int main() {
    int n;

    cout << "Nhap So Luong Phan Tu Cua Mang: ";
    cin >> n;

    int* arr = new int[n];
    
    cout << "Nhap Mang:\n";
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    
    cout << "=================MENU=================" << endl;
    cout << "1. Hien Thi Mang Hien Tai" << endl;
    cout << "2. Hien Thi Mang Dao Nguoc" << endl;
    cout << "3. Loc Phan Tu So Chan" << endl;
    cout << "4. Loc Phan Tu So Le" << endl;
    cout << "5. Sap Xep Mang Theo Thu Tu Tang Dan" << endl;
    cout << "6. Sap Xep Mang Theo Thu Tu Giam Dan" << endl;
    cout << "7. Loc So Nguyen To" << endl;
    cout << "8. Loc So Chinh Phuong" << endl;
    cout << "9. Them Phan Tu Vao Mang" << endl;
    cout << "10. Xoa Phan Tu Khoi Mang" << endl;
    cout << "11. Sua Phan Tu Trong Mang" << endl;
    cout << "12. Tim Kiem Phan Tu Trong Mang" << endl;
    cout << "13. Thoat Chuong Trinh" << endl;
    
    int opt;
    do {
        cout << "Hay Nhap Lua Chon Cua Ban: ";
        cin >> opt;
        
        switch (opt) {
            case 1:
                cout << "Mang Hien Tai La: ";
                defaultArr(n, arr);
                cout << "\n";
                break;
            case 2:
                cout << "Mang Dao Nguoc La: ";
                reverseArr(n, arr);
                cout << "\n";
                break;
            case 3:
                cout << "Cac So Chan La: ";
                arrEven(n, arr);
                cout << "\n";
                break;
            case 4:
                cout << "Cac So Le La: ";
                arrOdd(n, arr);
                cout << "\n";
                break;
            case 5:
                cout << "Mang Sap Xep Tang Dan La: ";
                sortArrAsc(n, arr);
                cout << "\n";
                break;
            case 6:
                cout << "Mang Sap Xep Giam Dan La: ";
                sortArrDesc(n, arr);
                cout << "\n";
                break;
            case 7:
                cout << "Cac So Nguyen To La: ";
                isPrime(n, arr);
                cout << "\n";
                break;
            case 8:
                cout << "Cac So Chinh Phuong La: ";
                isPerfectSquare(n, arr);
                cout << "\n";
                break;
            case 9:
                addElement(n, arr);
                cout << "\n";
                break;
            case 10:
                removeElement(n, arr);
                cout << "\n";
                break;
            case 11:
                modifyElement(n, arr);
                cout << "\n";
                break;
            case 12:
                searchElement(n, arr);
                cout << "\n";
                break;
            case 13:
                cout << "Thoat Chuong Trinh" << endl;
                break;
            default:
                cout << "Lua Chon Khong Hop Le" << endl;
                cout << "\n";
                break;
        }
        
    } while (opt != 13);

    delete[] arr;
    return 0;
}
