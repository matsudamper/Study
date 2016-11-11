class Program
    {
        static void Main(string[] args)
        {
            int[] data = { 8, 3, 5, 4, 9, 2, 6, 10, 7, 10 };
            quick_sort(data, 0, data.Length - 1);

            Print(data);

            Console.ReadLine();
        }

        static void quick_sort(int[] data, int start, int end)
        {
            if (start > end) return;

            int pivot = data[start];// 基準数
            
            int maxIndex = start + 1;
            int minIndex = end;

            while (true)
            {
                // 末尾から～先頭まで
                // 先頭まで小さいものがなかった時に入れ替えない対策
                while (minIndex > start)
                {
                    // 基準数より小さい数の場所で抜ける
                    if (data[minIndex] < pivot) break;

                    minIndex--;
                }

                // 基準点以降～後方のポイントまで
                while (maxIndex < minIndex)
                {
                    // 基準数より大きい数の場所で抜ける
                    if (data[maxIndex] > pivot) break;

                    maxIndex++;
                }

                // 重なっていた場合の基準点と重なっていた場所の交換
                // 重なっていなかった場合はそれぞれが見つけた大きい数と小さい数を交換
                if (minIndex <= maxIndex)
                {
                    data[start] = data[minIndex];
                    data[minIndex] = pivot;

                    quick_sort(data, start, minIndex - 1);
                    quick_sort(data, minIndex + 1, end);
                    break;
                }
                else
                {
                    int minbuf = data[minIndex];
                    data[minIndex] = data[maxIndex];
                    data[maxIndex] = minbuf;

                    maxIndex++;
                    minIndex--;
                }
            }
        }

        static void Print(int[] data)
        {
            Console.WriteLine(string.Join(", ", Array.ConvertAll(data, d => d.ToString())));
        }
    }