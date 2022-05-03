using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ApiHeroku.Data;
using ApiHeroku.Data.Model;

namespace ApiHeroku.Controllers
{
    [Route("api/user")]
    [ApiController]
    public class UserExampleController : ControllerBase
    {
        private readonly AppDbContext _context;

        public UserExampleController(AppDbContext context)
        {
            _context = context;
        }

        // GET: api/user
        [HttpGet]
        public async Task<ActionResult<IEnumerable<UserExample>>> GetUsers()
        {
            return await _context.UserInfosExample.ToListAsync();
        }


        //registration
        // POST: api/user
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<UserExample>> PostUser(UserExample userExample)
        {
            _context.UserInfosExample.Add(userExample);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetUserExample", new { id = userExample.ID }, userExample);
        }
    }


}
