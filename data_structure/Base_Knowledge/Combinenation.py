def cNk(self, n, k):
    if n - k < k:
        k = n - k

    if k == 0:
        return 1

    j = n
    up = 1
    while j > n - k:
        up *= j
        j -= 1
    down = 1
    j = 1
    while j <= k:
        down *= j
        j += 1
    return up // down