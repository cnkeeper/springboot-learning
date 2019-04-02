--[[
    不可重入锁
    1.加锁成功返回-1，失败返回pttl
]]--

local exists= redis.call('exists',KEYS[1])
if exists==0
then
   redis.call('set',KEYS[1],ARGV[1],'PX',ARGV[2],'NX')
   return -1
else
    return redis.call('pttl',KEYS[1])
end