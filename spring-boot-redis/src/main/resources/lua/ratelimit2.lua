local times = redis.call('incr', KEYS[1])

if times == 1 then
  redis.call('expire', KEYS[1], tonumber(ARGV[1]))
end

 return tostring(times)
