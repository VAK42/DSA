#include <iostream>
using namespace std;

void insertionSort(int arr[], int n) {
    int key, j;
    for (int i = 1; i < n; i++) {
        key = arr[i];
        j = i - 1;
        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = key;
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

    insertionSort(arr, n);
    cout << "Sorted Array: \n";
    printArray(arr, n);

    delete[] arr;
    return 0;
}
