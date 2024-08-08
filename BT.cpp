#include <iostream>
using namespace std;

struct Node {
    int data;
    Node *left;
    Node *right;
};

typedef Node *Tree;

Node *CreateNode(int init)
{
    Node *p = new Node;
    p->data = init;
    p->left = NULL;
    p->right = NULL;
    return p;
}

void CreateTree(Tree &root)
{
    root = NULL;
}

void DestroyTree(Tree &root)
{
    if (root)
    {
        DestroyTree(root->left);
        DestroyTree(root->right);
        delete root;
    }
}

void AddNode(Tree &root, Node *node)
{
    if (root)
    {
        if (root->data == node->data)
            return;
        if (node->data < root->data)
            AddNode(root->left, node);
        else
            AddNode(root->right, node);
    }
    else
    {
        root = node;
    }
}

Node *FindNode(Tree root, int x)
{
    if (root)
    {
        if (root->data == x)
            return root;
        if (x < root->data)
            return FindNode(root->left, x);
        return FindNode(root->right, x);
    }
    return NULL;
}

void PrintTree(Tree root)
{
    if (root)
    {
        PrintTree(root->left);
        cout << root->data << ' ';
        PrintTree(root->right);
    }
}

void NLR(Tree root)
{
    if (root)
    {
        cout << root->data << ' ';
        NLR(root->left);
        NLR(root->right);
    }
}

void LNR(Tree root)
{
    if (root)
    {
        LNR(root->left);
        cout << root->data << ' ';
        LNR(root->right);
    }
}

void LRN(Tree root)
{
    if (root)
    {
        LRN(root->left);
        LRN(root->right);
        cout << root->data << ' ';
    }
}

Node* FindMin(Tree root)
{
    while (root && root->left != NULL)
        root = root->left;
    return root;
}

void DeleteNode(Tree &root, int x)
{
    if (!root)
    {
        cout << "Not Found!\n";
        return;
    }
    
    if (x < root->data)
        DeleteNode(root->left, x);
    else if (x > root->data)
        DeleteNode(root->right, x);
    else
    {
        Node *temp = root;
        if (!root->left)
            root = root->right;
        else if (!root->right)
            root = root->left;
        else
        {
            Node *successor = FindMin(root->right);
            root->data = successor->data;
            DeleteNode(root->right, successor->data);
        }
        delete temp;
    }
}

int main()
{
    Tree tree;
    CreateTree(tree);
    int choice, value;
    
    cout << "\n==========Menu==========\n";
    cout << "1. Add A Node\n";
    cout << "2. Delete A Node\n";
    cout << "3. Print Tree (In-order)\n";
    cout << "4. Print Tree (Pre-order)\n";
    cout << "5. Print Tree (Post-order)\n";
    cout << "6. Find A Node\n";
    cout << "7. Exit\n";
    
    while (true)
    {
        cout << "Enter Your Choice: ";
        cin >> choice;
        switch (choice)
        {
        case 1:
            cout << "Enter Value To Add: ";
            cin >> value;
            AddNode(tree, CreateNode(value));
            break;
        case 2:
            cout << "Enter Value To Delete: ";
            cin >> value;
            DeleteNode(tree, value);
            break;
        case 3:
            cout << "Tree (In-order): ";
            PrintTree(tree);
            cout << endl;
            break;
        case 4:
            cout << "Tree (Pre-order): ";
            NLR(tree);
            cout << endl;
            break;
        case 5:
            cout << "Tree (Post-order): ";
            LRN(tree);
            cout << endl;
            break;
        case 6:
            cout << "Enter Value To Find: ";
            cin >> value;
            if (FindNode(tree, value))
                cout << "Node With Value " << value << " Found!" << endl;
            else
                cout << "Node With Value " << value << " Not Found!" << endl;
            break;
        case 7:
            DestroyTree(tree);
            cout << "Exiting...\n";
            return 0;
        default:
            cout << "Invalid Choice! Please Try Again!\n";
        }
    }
}
