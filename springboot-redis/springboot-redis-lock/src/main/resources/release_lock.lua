--[[
 功能：释放分布式锁，注意每个节点只能释放自己加的锁，别的节点无法释放其他节点的锁，
 1.节点获取锁的情况：
    > 无节点竞争锁
    > 获取锁节点主动释放锁/锁超时，此时节点竞争锁成功
]]--
local key=KEYS[1]
local value=ARGV[1]

local lockValue = redis.call('get',key)
if value==lockValue then
  redis.call('del',key)
  return 0
else
  return -1
end