

string add(string a, string b) {
    unsigned int digits = 18;
    unsigned int iterator = digits;
    unsigned long long int carry = 0;
    unsigned num = 0;
    unsigned long long int a11 = 0;
    unsigned long long int a22 = 0;

    string result = "";

    if (a.length() >= b.length()) {
        num = a.length();
        b = string(a.length() - b.length(), '0') + b;
    }
    else if (b.length() > a.length()) {
        num = b.length();
        a = string(b.length() - a.length(), '0') + a;
    }

    while (iterator <= num) {
        string a1 = a.substr(a.length() - iterator, digits);
        string a2 = b.substr(b.length() - iterator, digits);

        a11 = stoull(a1);
        a22 = stoull(a2);

        if (carry > 0) {
            a11 += carry;
            carry = 0;
        }
        
        unsigned long long int a33 = a11 + a22;
        string a44 = to_string(a33);

        if (a44.length() > digits) {
            carry = 1;
            a44 = a44.substr(1, digits);
        }
        
        if (a44.length() < digits) {
            a44 = string(digits - a44.length(), '0') + a44;
        }

        result = a44 + result;

        iterator += digits;
    }
    
    a11 = 0;
    a22 = 0;
    
    iterator -= digits;
    
    // cout << "iterator : " << iterator << endl;
    // cout << "a.length() : " << a.length() << endl;
    // cout << "b.length() : " << b.length() << endl;
    
    if (a.length() > iterator) {
        a11 = stoull(a.substr(0, a.length() - iterator));
        
        //cout << "final a11 : " << a11 << endl;
    }
    
    if (b.length() > iterator) {
        a22 = stoull(b.substr(0, b.length() - iterator));
        //cout << "final a22 : " << a22 << endl;
    }
    
    if (a11 + a22 + carry != 0) {
        result = to_string(a11 + a22 + carry) + result;
    }

    while (result.substr(0, 1) == "0") {
        result = result.substr(1, result.length() - 1);
    }

    return result;
}

string multiply(string a, string b) {
    unsigned int digits = 8;
    unsigned int iterator1 = digits;
    unsigned int iterator2 = digits;
    
    unsigned num = 0;
    unsigned long long int a11 = 1;
    unsigned long long int a22 = 1;

    string result = "0";

    if (a.length() >= b.length()) {
        num = a.length();
        b = string(a.length() - b.length(), '0') + b;
    }
    else if (b.length() > a.length()) {
        num = b.length();
        a = string(b.length() - a.length(), '0') + a;
    }

    while (iterator1 <= num) {
        string a1 = a.substr(a.length() - iterator1, digits);
        a11 = stoull(a1);
        //cout << "a11 : " << a11 << endl;
        iterator2 = digits;
        
        while (iterator2 <= num) {
            string a2 = b.substr(b.length() - iterator2, digits);
            a22 = stoull(a2);
            // cout << "iterator1 : " << iterator1 << endl;
            // cout << "iterator2 : " << iterator2 << endl;
            // cout << "a11 : " << a11 << endl;
            // cout << "a22 : " << a22 << endl;
            // cout << "zeroes : " << (iterator1 + iterator2) - (2*digits) << endl;
            unsigned long long int a33 = a11 * a22;
            string a44 = to_string(a33) + string((iterator1 + iterator2) - (2*digits), '0');
    
            result = add(a44, result);
    
            iterator2 += digits;
        }
        
        iterator2 -= digits;
        
        if (b.length() > iterator2) {
            a22 = stoull(b.substr(0, b.length() - iterator2));
            
            // cout << "iterator1 : " << iterator1 << endl;
            // cout << "iterator2 : " << iterator2 << endl;
            // cout << "a11 : " << a11 << endl;
            // cout << "a22 : " << a22 << endl;
            // cout << "zeroes : " << (iterator1 + iterator2) - digits << endl;
            unsigned long long int a33 = a11 * a22;
            string a44 = to_string(a33) + string((iterator1 + iterator2) - digits, '0');
            result = add(a44, result);
        }
        
        // cout << "====== iteration done ========" << endl;
        
        iterator1 += digits;
    }
    
    iterator1 -= digits;
        
    if (a.length() > iterator1) {
        a11 = stoull(a.substr(0, a.length() - iterator1));
        iterator2 = digits;
        
        while (iterator2 <= num) {
            string a2 = b.substr(b.length() - iterator2, digits);
            a22 = stoull(a2);
            // cout << "iterator1 : " << iterator1 << endl;
            // cout << "iterator2 : " << iterator2 << endl;
            // cout << "a11 : " << a11 << endl;
            // cout << "a22 : " << a22 << endl;
            // cout << "zeroes : " << (iterator1 + iterator2) - digits << endl;
            unsigned long long int a33 = a11 * a22;
            string a44 = to_string(a33) + string((iterator1 + iterator2) - digits, '0');
    
            result = add(a44, result);
    
            iterator2 += digits;
        }
        
        iterator2 -= digits;
        
        if (b.length() > iterator2) {
            a22 = stoull(b.substr(0, b.length() - iterator2));
            
            // cout << "iterator1 : " << iterator1 << endl;
            // cout << "iterator2 : " << iterator2 << endl;
            // cout << "a11 : " << a11 << endl;
            // cout << "a22 : " << a22 << endl;
            // cout << "zeroes : " << (iterator1 + iterator2) << endl;
            unsigned long long int a33 = a11 * a22;
            string a44 = to_string(a33) + string((iterator1 + iterator2), '0');
            result = add(a44, result);
        }
    }
    
    // cout << "====== iteration done ========" << endl;

    return result;
}