#include <iostream>
using namespace std;

void bubbleSort(int arr[], int n) {
    bool swapped;
    for (int i = 0; i < n-1; i++) {
        swapped = false;
        for (int j = 0; j < n-i-1; j++) {
            if (arr[j] > arr[j+1]) {
                swap(arr[j], arr[j+1]);
                swapped = true;
            }
        }
        if (!swapped)
            break;
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

    bubbleSort(arr, n);
    cout << "Sorted Array: \n";
    printArray(arr, n);

    delete[] arr;
    return 0;
}
