#include <iostream>
using namespace std;

void selectionSort(int arr[], int n) {
    int minIdx;
    for (int i = 0; i < n-1; i++) {
        minIdx = i;
        for (int j = i+1; j < n; j++) {
            if (arr[j] < arr[minIdx]) {
                minIdx = j;
            }
        }
        swap(arr[minIdx], arr[i]);
    }
}

void printArray(int arr[], int size) {
    for (int i = 0; i < size; i++)
        cout << arr[i] << " ";
    cout << endl;
}

int main() {
    int n;
    cout << "Enter The Number Of Elements: ";
    cin >> n;

    int* arr = new int[n];
    cout << "Enter The Elements:\n";
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    selectionSort(arr, n);
    cout << "Sorted Array: \n";
    printArray(arr, n);

    delete[] arr;
    return 0;
}
