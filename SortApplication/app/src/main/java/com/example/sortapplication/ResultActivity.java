package com.example.sortapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    TextView tvgt,tvsorted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Button btnthoat = (Button) findViewById(R.id.btnThoat);
        btnthoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tvgt = (TextView) findViewById(R.id.tvselectedgt);
        tvsorted = (TextView) findViewById(R.id.tvketquasx);

        Intent intentResult = getIntent();
        String gtselected = intentResult.getStringExtra("gtselected");
        tvgt.setText(gtselected);

        String strtoSort = intentResult.getStringExtra("strResult");
        String[] Itemstr = strtoSort.split(",");
        int[] Arrinput = new int[Itemstr.length];
        int i = 0;
        for(String item:Itemstr)
        {
            Arrinput[i] = Integer.parseInt(item);
            i++;
        }
        tvsorted.setText(strtoSort);

        int checkerro = 0;
       switch (gtselected)
        {
            case "Insertion":
                Insertion(Arrinput);
                break;
            case "Selection":
                Selection(Arrinput);
                break;
            case "Shell Sort":
                shellSort(Arrinput);
                break;
            case "Radix Sort":
                try{
//                    int Arrsoam[] = new int[Arrinput.length];
//                    int Arrsoduong[] = new int[Arrinput.length];
//                    int demsoam = 0,demsoduong = 0;
//                    for(int j = 0 ;j < Arrinput.length;i++)
//                    {
//
//                        if(Arrinput[j]<0)
//                        {
//                            Arrsoam[demsoam]=Arrinput[j];
//                            demsoam++;
//                        }
//                        else
//                        {
//                            Arrsoduong[demsoduong]=Arrinput[j];
//                            demsoduong++;
//                        }
//                    }
//
                    radixSort(Arrinput);
                }catch (Exception ex)
                {
                    checkerro = 1;
                }

                break;
            case "Quick Sort":
                QuickSort(Arrinput,0,Arrinput.length-1);
                break;
            case "Merge Sort":
                Mergesort(Arrinput,0,Arrinput.length-1);
                break;
            case "Bubble Sort":
                bubbleSort(Arrinput);
                break;
            case "Interchange":
                interchangeSort(Arrinput);
                break;
        }
       String strafterSort="";
       for(i = 0; i < Arrinput.length;i++ ) {
           strafterSort = strafterSort + Arrinput[i] + ",";
       }
       if(checkerro == 1)
       {
           tvsorted.setText("Thuật toán Radix Sort không thể sắp xếp dãy số có chứa số nguyên âm");
       }
       else
       {
           tvsorted.setText(strafterSort.substring(0,strafterSort.length()-1));
       }

    }
    void Insertion(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            // Di chuyển các phần tử của arr [0 ... i - 1], lớn hơn key
            // đến một vị trí trước vị trí hiện tại của chúng
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

     public void Selection(int arr[]) {
        int n = arr.length;

        // Duyệt qua từng phần tử của mảng
        for (int i = 0; i < n - 1; i++) {

            // Tìm phần tử nhỏ nhất trong mảng chưa được sắp xếp
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Hoán đổi phần tử nhỏ nhất và phần tử đầu tiên
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
    //QuickSort
    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {

            // Nếu phần tử hiện tại nhỏ hơn chốt
            if (arr[j] < pivot) {
                i++;

                // swap arr[i] và arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap arr[i+1] và arr[high] (hoặc pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // arr[] --> Mảng cần được sắp xếp,
    // low --> chỉ mục bắt đầu,
    // high --> chỉ mục kết thúc
    void QuickSort(int arr[], int low, int high) {
        if (low < high) {

            // pi là chỉ mục của chốt, arr[pi] vị trí của chốt
            int pi = partition(arr, low, high);

            // Sắp xếp đệ quy các phần tử
            // trướcphân vùng và sau phân vùng
            QuickSort(arr, low, pi - 1);
            QuickSort(arr, pi + 1, high);
        }
    }
    // Hàm sắp xếp nổi bọt
    public void bubbleSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] và arr[i]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }
    // Merge hai mảng con của arr[].
    // Mảng con thứ nhất là arr[l..m]
    // Mảng con thứ hai là arr[m+1..r]
    void merge(int arr[], int l, int m, int r) {

        // Tìm kích thước của 2 mảng con để merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Tạo mảng tạm
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy dữ liệu vào mảng tạm
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge các mảng tạm lại

        // Chỉ mục ban đầu của 2 mảng con
        int i = 0, j = 0;

        // Chỉ mục ban đầu của mảng phụ được hợp nhất
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Sao chép các phần tử còn lại của L[] nếu có
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Sao chép các phần tử còn lại của R[] nếu có
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    void Mergesort(int arr[], int l, int r) {
        if (l < r) {

            // Tìm điểm chính giữa
            int m = (l + r) / 2;

            // Hàm đệ quy tiếp tục chia đôi
            Mergesort(arr, l, m);
            Mergesort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }
    //Shell sort
    public void shellSort(int arr[]) {
        int inner, outer;
        int valueToInsert;
        int interval = 1;
        int elements = arr.length;
        int i = 0;

        while (interval <= elements / 3) {
            interval = interval * 3 + 1;
        }

        while (interval > 0) {
            for (outer = interval; outer < elements; outer++) {
                valueToInsert = arr[outer];
                inner = outer;

                while (inner > interval - 1 && arr[inner - interval] >= valueToInsert) {
                    arr[inner] = arr[inner - interval];
                    inner -= interval;
                    System.out.println(" Di chuyen phan tu: " + arr[inner]);
                }

                arr[inner] = valueToInsert;
                System.out.println(" Chen phan tu: " + valueToInsert
                        + ", tai vi tri: " + inner);
            }

            interval = (interval - 1) / 3;
            i++;
        }
    }
    //Radix sort
//    public void radixSort(int[] A,int n) {
//        int i, m = A[0], exp = 1;
//        int[] B = new int[n];
//        for (i = 1; i < n; i++) //tìm số lớn nhất trong dãy
//        {
//            if (A[i] > m) {
//                m = A[i];
//            }
//        }
//        while (m / exp > 0)
//        {
//            int[] bucket = new int[10];
//            for (i = 0; i < n; i++) //đếm phân bố các số từ 0..9
//            {
//                bucket[(A[i] / exp) % 10]++;
//            }
//            for (i = 1; i < 10; i++) {
//                bucket[i] += bucket[i - 1];
//            }
//            for (i = n - 1; i >= 0; i--) {
//                B[--bucket[(A[i] / exp) % 10]] = A[i];
//            }
//            for (i = 0; i < n; i++) {
//                A[i] = B[i];
//            }
//            exp *= 10;
//        }
//    }

    ///////////////////////////////////////////////////////////////////////////////////////////////
//    int getMax(int arr[], int n)
//    {
//        int mx = arr[0];
//        for (int i = 1; i < n; i++)
//            if (arr[i] > mx)
//                mx = arr[i];
//        return mx;
//    }
//
//    // A function to do counting sort of arr[] according to
//// the digit represented by exp.
//    void countSort(int arr[], int n, int exp)
//    {
//        int output[] = new int[n] ; // output array
//        int i, count[] = new int[]{0,0,0,0,0,0,0,0,0,0};
//
//        // Store count of occurrences in count[]
//        for (i = 0; i < n; i++)
//            count[(arr[i] / exp) % 10]++;
//
//        // Change count[i] so that count[i] now contains actual
//        //  position of this digit in output[]
//        for (i = 1; i < 10; i++)
//            count[i] += count[i - 1];
//
//        // Build the output array
//        for (i = n - 1; i >= 0; i--) {
//            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
//            count[(arr[i] / exp) % 10]--;
//        }
//
//        // Copy the output array to arr[], so that arr[] now
//        // contains sorted numbers according to current digit
//        for (i = 0; i < n; i++)
//            arr[i] = output[i];
//    }
//
//    // The main function to that sorts arr[] of size n using
//// Radix Sort
//    void radixSort(int arr[], int n)
//    {
//        // Find the maximum number to know number of digits
//        int m = getMax(arr, n);
//
//        // Do counting sort for every digit. Note that instead
//        // of passing digit number, exp is passed. exp is 10^i
//        // where i is current digit number
//        for (int exp = 1; m / exp > 0; exp *= 10)
//            countSort(arr, n, exp);
//    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private int[] radixSort(int[] arr) {
        // base, during the cycle automatically increase according to the size of the number of
        int digitNumber = 1;
        // barrels, positive and negative numbers a total of 20 barrels
        int[][] bucket = new int[20][arr.length > 19 ? arr.length : 20];
        // i represents the current cycle of base, such as 10, 100 ....
        for (int i = 1, arrOrder = 0; i <= digitNumber;arrOrder = 0) {
            // Indicates whether the current cycle has been expanded base
            boolean digitExpand = false;
            // the number of the current cycle number of buckets each bucket 20 stored
            int[] numberAmount = new int[20];
            // into the bucket
            for (int num : arr) {
                // digit represents the num which should be placed in a bucket 20 squares
                int digit = (num / i) % 10;
                // Here is plus 10, that is, after several positive with 10 buckets, barrels before negative with 10
                digit += 10;
                // numberAmount [digit] initial value 0 can be used directly
                bucket[digit][numberAmount[digit]++] = num;
                //Outermost loop // when the conditions need to be expanded once the current cycle have met the following conditions, namely the base multiplied by 10
                // such as the first time through the loop digitNumber = 1, the current num = 2, then without increasing the
                // num = 10 if you need to expand once the outermost loop
                if (num >= (digitNumber * 10) && !digitExpand) {
                    digitNumber *= 10;
                    digitExpand = true;
                }
            }
            // retrieve data from the tub 20, to complete a sort
            for (int j = 0; j < 20; j++) {
                for (int k = 0; k < numberAmount[j]; k++) {
                    arr[arrOrder++] = bucket[j][k];
                }
            }
            // Each time through the loop i need to multiply 10
            i *= 10;
        }

        return arr;
    }
    //InterchangeSort
    public void interchangeSort(int A[])
    {
        for (int i = 0; i < A.length-1; i++) {
            for (int j = i + 1; j < A.length; j++) {
                if (A[i] > A[j]) {
                    int temp = A[i];
                    A[i] = A[j];
                    A[j] = temp;
                }
            }

        }
    }


}