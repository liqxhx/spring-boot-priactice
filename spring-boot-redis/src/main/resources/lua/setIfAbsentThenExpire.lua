local rt=redis.call('SETNX', KEYS[1], ARGV[1])
if rt == 1 then
redis.call('EXPIRE', KEYS[1], tonumber(ARGV[2]))
return true
end
return false