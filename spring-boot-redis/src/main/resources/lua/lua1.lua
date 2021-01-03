local rt=redis.call('ZRANGEBYSCORE', KEYS[1], ARGV[1], ARGV[2])
if rt then
redis.call('ZREMRANGEBYSCORE', KEYS[1], ARGV[1], ARGV[2])
return rt
end
return null