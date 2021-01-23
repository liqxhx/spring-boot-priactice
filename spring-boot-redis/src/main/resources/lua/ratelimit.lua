local num = redis.call('incr', KEYS[1])
 if num == 1 then
 redis.call('expire', KEYS[1], ARGV[1])
 end
return tostring(num)
