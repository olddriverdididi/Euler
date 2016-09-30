#工程说明

    本工程创建的目的是为了在归并解题过中遇到的高频问题，如素数的处理，排列组合等问题，而创立的工具及解题工程。工程本身以Java语言书写，你有可以自己实现这个工程的其它版本。   
    目前包含素数工具类和排列组合工具类，下面介绍已有工具类的使用方法。    
  
##Prime类说明

    Prime类用于处理素数相关问题，Prime类被设计为工具类，不允许创建实例，也不允许被继承，故以final修饰Prime类，并以private修饰默认构造器。
Prime类本身维护一个从2开始的素数数组，初始素数数组长度为1000，在进行各种处理时会用到这个数组，特称由Prime类维护的素数数组为**类数组**。并且维护两个类变量，一个代表类数组的长度，一个代表类数组的最大值。
    从效率考虑，在调用getPrimeArrayByLength和getPrimeArrayBelow函数时，如果请求的数组长度长于类数组，则会替换类数组，否则会直接返回原类数组的子集。除提供基本的isPrime函数判断一个整数是否为素数外，还提供一个利用类数组快速判断的isQuickPrime方法，以及另一个判断大数是否为素数的函数isProbablePrime(long num, int certainty)，这个函数不是绝对可靠的，可靠性为1-1/(2^certainty)。
### 函数列表
- **boolean isPrime(int)**
    判断一个int整数是否为素数
- **int[] getPrimeArrayByLength(int length)**
    返回长度为length的素数数组
- **int[] getPrimeArrayBelow(int num)**
    返回小于等于num的素数数组
- **int getPrimeIndex(int num)**
    返回num在Prime类维护数组中的索引，找不到返回-1
- **int binarySearch(int[] arr, int key)**
    在数组arr中查找key，如果找到则返回索引，找不到返回-1
- **int binarySearch(int[] arr, int key, boolean isStrict)**
    在数组arr中查找key，如果isStrict为false，则与binarySearch(int[],int)函数功能相同，如果为true，则在找不到时返回数组中小于key的最大值的索引
- **boolean check()**
    检查内部变量是否出错，不合法返回false并校正，否则返回true
- **boolean isQuickPrime(int num)**
    利用素数类维护的数组快速判断num是否为素数
- **boolean isProbablePrime(long num, int certainty)**
    采用Miller-Rabin算法判断一个大整数是否为素数，可靠性为1-1/(2^certainty)
- **int[] getPrimeArray()**
    返回素数类维护的素数数组
- **int getPrimeArrayLength()**
    返回素数类维护的素数数组的长度
- **int getMaxCheckedPrime()**
    返回素数类维护的素数数组的最大值

##Permutation类说明
	暂无