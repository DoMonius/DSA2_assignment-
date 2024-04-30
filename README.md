Data Compression 

DEFLATE (used in zlib): DEFLATE combines the LZ77 algorithm (like LZW)
with Huffman coding to achieve higher compression ratios. It is widely used and supported in many compression utilities and libraries.

•	Compression: The worst-case time complexity of DEFLATE compression is O(n log n),
where n is the size of the input data. This is because DEFLATE involves building and
sorting Huffman trees, which have a time complexity of O(n log n).



•	Decompression: The worst-case time complexity of DEFLATE decompression is also O(n log n), 
where n is the size of the compressed data. This is because decompression involves reconstructing
the original data by traversing the Huffman tree, which also has a time complexity of O(n log n).
