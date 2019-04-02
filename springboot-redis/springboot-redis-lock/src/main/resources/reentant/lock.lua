--[[
  可重入分布式锁
  1.采用hash结构存储：
        lock_name:
            nodeId-threadId: reentrantCount
]]--

local lock_name=KEYS[1]
local nodeIdThreadId=KEYS[2]
local lockTime=ARGV[1]

if (redis.call('exists',lock_name)==0) then
   redis.call('hset',lock_name,nodeIdThreadId,1);
   redis.call('pexpire',lock_name,lockTime);
   return 1;
end

if (redis.call('hexists',lock_name,nodeIdThreadId)==1) then
   redis.call('hincrby',lock_name,nodeIdThreadId, 1);
   redis.call('pexpire',lock_name,lockTime);
   return 1;
end
return 0